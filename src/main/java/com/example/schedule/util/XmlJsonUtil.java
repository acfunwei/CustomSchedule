package com.example.schedule.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author ZW
 * 该类负责处理webService返回的报文生成JSON
 */
public class XmlJsonUtil {

    /**
     * 从params中获取key以JSONArray形式取出
     * @param params
     * @param key
     * @return
     */
    public static JSONArray getJsonArray(JSONObject params, String key){
        Object o = params.get(key);
        JSONArray ja = new JSONArray();
        if(o == null){
            return ja;
        }
        if(o instanceof Map){
            ja.add(new JSONObject((Map) o));
        }
        if(o instanceof Collection){
            ja.addAll((Collection<?>) o);
        }
        return ja;
    }

    /**
     * 将XML转换成JSON格式
     * @param xmlDocument
     * @param tier
     * @return
     * @throws Exception
     */
    public static JSONObject parseXmlToJson(String xmlDocument, int tier) throws Exception{
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

    private XmlJsonUtil(){}

}
