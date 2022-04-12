package com.example.schedule.config;

import com.example.schedule.config.locker.CustomLock;
import com.example.schedule.config.locker.DefaultCustomLock;
import com.example.schedule.config.logger.DefaultScheduleLogger;
import com.example.schedule.config.logger.ScheduleLogger;
import com.example.schedule.service.CustomScheduleService;
import com.example.schedule.sys.CustomScheduleCommandLine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.ErrorHandler;

/**
 * TODO 缺少锁类型获取
 * @author ZW
 */
@Configuration
@ComponentScan("com.example.schedule")
public class ScheduleThreadEnableAutoConfiguration {

    @Bean
    public TaskScheduler scheduledThreadPoolExecutor(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler-");
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        return threadPoolTaskScheduler;
    }

    @Bean
    public CustomScheduleService customScheduleService(){
        return new CustomScheduleService();
    }

    @Bean
    public CustomLock customLock(){
        return new DefaultCustomLock();
    }

    @Bean
    public ScheduleLogger scheduleLogger(){
        return new DefaultScheduleLogger();
    }

}
