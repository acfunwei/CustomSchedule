package com.example.schedule.config.logger;

import com.example.schedule.sys.bean.FbpSysJobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZW
 */
public class DefaultScheduleLogger implements ScheduleLogger{
    private static Logger logger = LoggerFactory.getLogger(DefaultScheduleLogger.class);

    @Override
    public void logger(FbpSysJobs fbpSysJobs, String message) {
        logger.info("定时任务[{}]:{}", fbpSysJobs.getJobCode(), message);
    }
}
