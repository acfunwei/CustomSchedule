package com.example.schedule.service;

import com.example.schedule.config.bean.CustomScheduleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * TODO 缺少数据库操作
 * @author ZW
 */
public class CustomScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomScheduleService.class);

    private static Map<String, CustomScheduleBean> customScheduleBeanMap = new ConcurrentHashMap<>(1>>5);

    @Autowired
    private TaskScheduler taskScheduler;

    /**
     *
     * @param customScheduleBean
     */
    public void addTask(CustomScheduleBean customScheduleBean){
        //不接受多个相同的任务
        if(customScheduleBeanMap.containsKey(customScheduleBean.getTaskName())){
            return;
        }
        ScheduledFuture<?> schedule = taskScheduler.schedule(customScheduleBean, customScheduleBean.getCronTrigger());
        customScheduleBean.setFuture(schedule);
        customScheduleBeanMap.put(customScheduleBean.getTaskName(), customScheduleBean);
        //TODO 此处缺少数据库或者Redis中的操作数据
    }

    /**
     *
     * @param taskName
     */
    public void removeTask(String taskName){

        CustomScheduleBean customScheduleBean1 = customScheduleBeanMap.remove(taskName);
        customScheduleBean1.cancel();
        //TODO 此处缺少数据库或者Redis中的操作数据
    }

    /**
     *
     * @param customScheduleBean
     */
    public void UpdateTask(CustomScheduleBean customScheduleBean){
        removeTask(customScheduleBean.getTaskName());
        addTask(customScheduleBean);
    }


    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }
}
