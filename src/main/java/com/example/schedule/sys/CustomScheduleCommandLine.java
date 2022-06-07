package com.example.schedule.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.schedule.config.bean.CustomScheduleBean;
import com.example.schedule.mapper.FbpSysJobsMapper;
import com.example.schedule.service.CustomScheduleService;
import com.example.schedule.sys.bean.FbpSysJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Wrapper;
import java.util.List;

/**
 * @author ZW
 */
@Component
public class CustomScheduleCommandLine implements CommandLineRunner {

    @Autowired
    private FbpSysJobsMapper fbpSysJobsMapper;
    @Autowired
    private CustomScheduleService customScheduleService;
    @Override
    public void run(String... args) throws Exception {
        QueryWrapper wrapper = new QueryWrapper<FbpSysJobs>();
        wrapper.eq("enableFlag", 1);
        List<FbpSysJobs> fbpSysJobs = fbpSysJobsMapper.selectList(wrapper);
        for (FbpSysJobs fbpSysJob : fbpSysJobs) {
            customScheduleService.addTask(CustomScheduleBean.getInstance(fbpSysJob));
        }
    }
}
