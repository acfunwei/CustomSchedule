package com.example.schedule.service;

import com.example.schedule.config.bean.CustomScheduleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
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
        if(customScheduleBeanMap.containsKey(customScheduleBean.getId())){
            return;
        }
        ScheduledFuture<?> schedule = taskScheduler.schedule(customScheduleBean, customScheduleBean.getTrigger());
        customScheduleBean.setFuture(schedule);
        customScheduleBeanMap.put(customScheduleBean.getId(), customScheduleBean);
    }

    /**
     *
     * @param taskId
     */
    public void removeTask(Long taskId){
        CustomScheduleBean customScheduleBean1 = customScheduleBeanMap.remove(taskId);
        customScheduleBean1.cancel();
    }

    /**
     *
     * @param customScheduleBean
     */
    public void updateTask(CustomScheduleBean customScheduleBean) throws Exception {
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
