package com.example.schedule.service;

import com.example.schedule.config.bean.CustomScheduleBean;
import com.example.schedule.sys.bean.FbpSysJobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * TODO 缺少数据库操作
 * @author ZW
 */
@Component
public class CustomScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomScheduleService.class);

    private static Map<Long, CustomScheduleBean> customScheduleBeanMap = new ConcurrentHashMap<>(1>>5);

    @Autowired
    private TaskScheduler taskScheduler;

    /**
     *
     * @param
     */
    public void addTask(CustomScheduleBean customScheduleBean) throws Exception {
        //不接受多个相同的任务

//        CustomScheduleBean customScheduleBean = CustomScheduleBean.getInstance(fbpSysJobs);
        if(customScheduleBeanMap.containsKey(customScheduleBean.getId())){
            return;
        }
        ScheduledFuture<?> schedule = taskScheduler.schedule(customScheduleBean, customScheduleBean.getTrigger());
        customScheduleBean.setFuture(schedule);
        customScheduleBeanMap.put(customScheduleBean.getId(), customScheduleBean);
        //TODO 此处缺少数据库或者Redis中的操作数据

    }

    /**
     *
     * @param taskId
     */
    public void removeTask(Long taskId){

        CustomScheduleBean customScheduleBean1 = customScheduleBeanMap.remove(taskId);
        customScheduleBean1.cancel();
        //TODO 此处缺少数据库或者Redis中的操作数据
    }

    /**
     *
     * @param customScheduleBean
     */
    public void UpdateTask(CustomScheduleBean customScheduleBean) throws Exception {
        removeTask(customScheduleBean.getId());
        addTask(customScheduleBean);
    }


    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }
}
