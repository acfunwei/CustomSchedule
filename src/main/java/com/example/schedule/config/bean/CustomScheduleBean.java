package com.example.schedule.config.bean;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.schedule.config.locker.CustomLock;
import com.example.schedule.config.logger.ScheduleLogger;
import com.example.schedule.sys.bean.FbpSysJobs;
import com.example.schedule.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * TODO 缺少锁操作
 * @author ZW
 */
public abstract class CustomScheduleBean implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomScheduleBean.class);

    volatile ScheduledFuture<?> future;
    @Autowired
    private CustomLock customLock;
    @Autowired
    private ScheduleLogger scheduleLogger;

    private FbpSysJobs fbpSysJobs;
    public void cancel(){
        if(this.future == null){
            return;
        }
        this.future.cancel(false);
    }

    public static CustomScheduleBean getInstance(FbpSysJobs fbpSysJobs) throws Exception {
        CustomScheduleBean customScheduleBean = (CustomScheduleBean) ApplicationUtil.applicationContext.getBean(fbpSysJobs.getJobType());
        customScheduleBean.setFbpSysJobs(fbpSysJobs);
        return customScheduleBean;
    }
    @Override
    public void run() {
        //获取锁
        boolean locker = customLock.locker(fbpSysJobs.getJobCode());
        if(!locker){
           return;
        }
        LOGGER.info("任务[{}]开始", fbpSysJobs.getJobCode());
        String errorMsg = "";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            taskInvoke(Optional.ofNullable(JSON.parseObject(fbpSysJobs.getParameter())).orElseGet(JSONObject::new));
        } catch (Exception e){
            errorMsg = Arrays.toString(e.getStackTrace());
            if(errorMsg.length() > 2000){
                errorMsg = errorMsg.substring(0, 2000);
            }
        } finally {
            //完成解锁
            stopWatch.stop();
//            LOGGER.info("任务[{}]完成共花费{}秒", fbpSysJobs.getJobCode(), stopWatch.getTotalTimeMillis());
            scheduleLogger.logger(fbpSysJobs, StringUtils.isEmpty(errorMsg) ? MessageFormat.format("执行成功共花费{0}ms", stopWatch.getTotalTimeMillis()) : errorMsg);
            customLock.unlock(fbpSysJobs.getJobCode());
        }

    }

    /**
     * 不同的定时任务对应的执行过程
     * @param params
     */
    abstract void taskInvoke(JSONObject params) throws Exception;

    public Trigger getTrigger(){
        if(!StringUtils.isEmpty(fbpSysJobs.getCornExpress())){
            return new CronTrigger(fbpSysJobs.getCornExpress());
        }
        if(!StringUtils.isEmpty(fbpSysJobs.getTimeUnit())){
            TimeUnit timeUnit = TimeUnit.valueOf(fbpSysJobs.getTimeUnit());
            return new PeriodicTrigger(fbpSysJobs.getIntervalNum(), timeUnit);
        }
        throw new IllegalArgumentException(MessageFormat.format("定时任务[{0}]参数配置错误请检查定时器触发方式", fbpSysJobs.getJobCode()));
    }

    public ScheduledFuture<?> getFuture() {
        return future;
    }

    public void setFuture(ScheduledFuture<?> future) {
        this.future = future;
    }

    public FbpSysJobs getFbpSysJobs() {
        return fbpSysJobs;
    }

    public void setFbpSysJobs(FbpSysJobs fbpSysJobs) {
        this.fbpSysJobs = fbpSysJobs;
    }

    public Long getId(){return fbpSysJobs.getId();};
}
