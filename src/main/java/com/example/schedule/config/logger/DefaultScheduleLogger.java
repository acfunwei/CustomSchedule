package com.example.schedule.config.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZW
 */
public class DefaultScheduleLogger implements ScheduleLogger{
    private static Logger logger = LoggerFactory.getLogger(DefaultScheduleLogger.class);

    @Override
    public void logger(String logStr) {
        logger.info(logStr);
    }
}
