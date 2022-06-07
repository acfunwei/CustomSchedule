package com.example.schedule.config.logger;

import com.example.schedule.sys.bean.FbpSysJobs;

/**
 * @author ZW
 */
public interface ScheduleLogger {
    /**
     * 执行日志
     * @param fbpSysJobs
     * @param message
     */
    void logger(FbpSysJobs fbpSysJobs, String message);

}
