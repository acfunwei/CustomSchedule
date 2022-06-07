package com.example.schedule.config.bean;


import com.alibaba.fastjson2.JSONObject;
import com.example.schedule.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author ZW
 */
@Component
@Scope("prototype")
public class MethodCustomScheduleBean extends CustomScheduleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodCustomScheduleBean.class);

    @Override
    void taskInvoke(JSONObject params) throws Exception {
        String beanName = params.getString("beanName");
        String methodName = params.getString("methodName");
        Object bean = ApplicationUtil.applicationContext.getBean(beanName);
        Method method = Arrays.stream(bean.getClass().getDeclaredMethods()).filter(a -> {
            return a.getName().equals(methodName);
        }).findFirst().orElseThrow(IllegalArgumentException::new);
        method.invoke(bean, params);
    }
}
