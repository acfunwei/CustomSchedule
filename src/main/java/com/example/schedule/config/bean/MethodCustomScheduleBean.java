package com.example.schedule.config.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ZW
 */
@Component
@Scope("prototype")
public class MethodCustomScheduleBean extends CustomScheduleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodCustomScheduleBean.class);
    @Override
    void taskInvoke(String taskName) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
