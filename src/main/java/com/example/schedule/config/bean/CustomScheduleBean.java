package com.example.schedule.config.bean;

import com.example.schedule.config.locker.CustomLock;
import com.example.schedule.config.logger.ScheduleLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StopWatch;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * TODO 缺少锁操作
 * @author ZW
 */
public abstract class CustomScheduleBean implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomScheduleBean.class);

    volatile ScheduledFuture<?> future;

    private String cronExpress;

    private String taskName;

    @Autowired
    private CustomLock customLock;
    @Autowired
    private ScheduleLogger scheduleLogger;

    public void cancel(){
        if(this.future == null){
            return;
        }
        this.future.cancel(false);
    }

    @Override
    public void run() {
        //获取锁
        boolean locker = customLock.locker(taskName);
        if(!locker){
           return;
        }
        LOGGER.info("任务[{}]开始", taskName);
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            taskInvoke(taskName);
            stopWatch.stop();
            LOGGER.info("任务[{}]完成共花费{}秒", taskName, stopWatch.getTotalTimeSeconds());
        }finally {
            //完成解锁
            scheduleLogger.logger(taskName + "执行完成");
            customLock.unlock(taskName);
        }

    }

    /**
     * 不同的定时任务对应的执行过程
     * @param taskName
     */
    abstract void taskInvoke(String taskName);

    public String getCronExpress() {
        return cronExpress;
    }

    public void setCronExpress(String cronExpress) {
        this.cronExpress = cronExpress;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public CronTrigger getCronTrigger(){
        return new CronTrigger(this.cronExpress);
    }

    public ScheduledFuture<?> getFuture() {
        return future;
    }

    public void setFuture(ScheduledFuture<?> future) {
        this.future = future;
    }
}
