package com.example.schedule.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.schedule.config.bean.CustomScheduleBean;
import com.example.schedule.mapper.FbpSysJobsMapper;
import com.example.schedule.sys.bean.FbpSysJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FbpSysJobsService {

    @Autowired
    private FbpSysJobsMapper fbpSysJobsMapper;
    @Autowired
    private CustomScheduleService customScheduleService;

    @Transactional(rollbackFor = Exception.class)
    public void insert(FbpSysJobs fbpSysJobs) throws Exception {
        fbpSysJobsMapper.insert(fbpSysJobs);
        customScheduleService.addTask(CustomScheduleBean.getInstance(fbpSysJobs));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(FbpSysJobs fbpSysJobs) throws Exception {
        fbpSysJobsMapper.updateById(fbpSysJobs);
        customScheduleService.updateTask(CustomScheduleBean.getInstance(fbpSysJobs));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(FbpSysJobs fbpSysJobs) throws Exception {
        fbpSysJobsMapper.deleteById(fbpSysJobs.getId());
        customScheduleService.removeTask(fbpSysJobs.getId());
    }

    public List<FbpSysJobs> selectList(FbpSysJobs fbpSysJobs){
        QueryWrapper<FbpSysJobs> fbpSysJobsQueryWrapper = new QueryWrapper<>();
        fbpSysJobsQueryWrapper.setEntity(fbpSysJobs);
        return fbpSysJobsMapper.selectList(fbpSysJobsQueryWrapper);
    }
}
