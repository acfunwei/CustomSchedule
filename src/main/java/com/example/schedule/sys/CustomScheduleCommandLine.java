package com.example.schedule.sys;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ZW
 */
@Component
public class CustomScheduleCommandLine implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("查询数据库");
    }
}
