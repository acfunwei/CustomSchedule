package com.example.schedule.config.annotation;

import com.example.schedule.config.ScheduleThreadEnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用该注解开启自定义的定时任务
 * @author ZW
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(ScheduleThreadEnableAutoConfiguration.class)
public @interface EnableCustomSchedule {
}
