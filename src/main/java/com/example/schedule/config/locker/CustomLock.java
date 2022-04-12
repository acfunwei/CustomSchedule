package com.example.schedule.config.locker;

/**
 * 该方法为任务锁，适用于集群模式下，具体实现可以为redis，zookeeper等多任务下允许启动一个
 * @author ZW
 */
public interface CustomLock {

    boolean locker(String taskName);

    void unlock(String taskName);
}
