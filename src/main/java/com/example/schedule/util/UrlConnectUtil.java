package com.example.schedule.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.sun.jndi.toolkit.url.UrlUtil;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.xml.internal.fastinfoset.sax.SAXDocumentSerializer;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import com.sun.xml.internal.ws.streaming.DOMStreamReader;
import com.thoughtworks.xstream.XStream;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.XmlStreamReader;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sun.nio.ch.IOUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ZW
 */
public class UrlConnectUtil {

    private UrlConnectUtil() {
    }

    /**
     * 请求Restful数据
     *
     * @return
     */
    public static JSONObject doGetRaw(String url, String templateStr, Object params) throws Exception {
        //使用Freemark进行模板化请求报文
        String requestBody = templateStr;
        if (params == null) {
            Template template = new Template("", templateStr, new Configuration(Configuration.VERSION_2_3_30));
            requestBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        }
        return JSON.parseObject(doGet(url, requestBody, "GET"));
    }

    /**
     *
     * @param url 请求地址URL
     * @param templateStr 模板
     * @param params 请求参数
     * @param tier JSON解析第几层开始 初始为1
     * @return
     * @throws Exception
     */
    public static JSONObject doGetWsdl(String url, String templateStr, Object params, int tier) throws Exception {
        //使用Freemarker进行模板化请求报文
        String requestBody = templateStr;
        if (params != null) {
            Template template = new Template("", templateStr, new Configuration(Configuration.VERSION_2_3_30));
            requestBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        }
        String result = doGet(url, requestBody, "GET");
        return parseXmlToJson(result, tier);
    }

    private static JSONObject parseXmlToJson(String xmlDocument, int tier) throws Exception{
        try(StringReader stringReader = new StringReader(xmlDocument);){
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(stringReader));
            Element documentElement = document.getDocumentElement();
            JSONObject result = new JSONObject();
            parseXmlNodeList(documentElement.getChildNodes(), result);
            String next = "";
            for (int i = 0; i < tier; i++) {
                next = result.keySet().iterator().next();
                result = result.getJSONObject(next);
            }
            return result;
        }
    }

    private static void parseXmlNodeList(NodeList nodeList, JSONObject result){
        for (int i = 0, len = nodeList.getLength(); i < len; i++) {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                if(node.hasChildNodes()){
                    String nodeName = node.getNodeName();
                    if(nodeName.contains(":")){
                        nodeName = nodeName.split(":")[1];
                    }
                    if(node.getChildNodes().getLength() == 1 && node.getFirstChild().getNodeType() == Node.TEXT_NODE){
                        result.put(nodeName, node.getFirstChild().getNodeValue());
                        continue;
                    }
                    //新建一个JSONObject
                    JSONObject childResult = new JSONObject();
                    parseXmlNodeList(node.getChildNodes(), childResult);
                    if(result.containsKey(nodeName)){
                        if(result.get(nodeName) instanceof List){
                            result.getJSONArray(nodeName).add(childResult);
                            continue;
                        }
                        JSONArray ja = new JSONArray();
                        ja.add(result.get(nodeName));
                        result.replace(nodeName, ja);
                        continue;
                    }
                    result.put(nodeName, childResult);
                }
            }
        }
    }

    /**
     * 基本的请求方法
     *
     * @param url
     * @param requestBody
     * @return
     * @throws Exception
     */
    public static String doGet(String url, String requestBody, String method) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestMethod(method);
        try (OutputStream outputStream = urlConnection.getOutputStream();) {
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }
        urlConnection.connect();
        if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = urlConnection.getInputStream();) {
                return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }
        }
        try (InputStream errorStream = urlConnection.getErrorStream();) {
            throw new RuntimeException(IOUtils.toString(errorStream, StandardCharsets.UTF_8));
        }
    }

}
