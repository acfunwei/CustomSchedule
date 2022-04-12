package com.example.schedule.config.locker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 多个任务仅允许启动一个任务
 * @author ZW
 */
public class DefaultCustomLock implements CustomLock{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCustomLock.class);
    private static Set<String> lockSet = new LinkedHashSet<>(1>>5);


    @Override
    public boolean locker(String taskName) {
        LOGGER.info("任务[{}]获取锁...", taskName);
        if (lockSet.contains(taskName)){
            return false;
        }
        lockSet.add(taskName);
        return true;
    }

    @Override
    public void unlock(String taskName) {
        LOGGER.info("任务[{}]解锁...", taskName);
        lockSet.remove(taskName);
    }

}
