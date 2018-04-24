package com.cn.lianshou.service.Impl;

import com.cn.lianshou.entity.JobInfo;
import com.cn.lianshou.mapper.JobInfoMapper;
import com.cn.lianshou.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author: Wanghh
 * Date: 2018/4/13 14:42
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoMapper jobInfoMapper;



    @Override
    public List<JobInfo> query(Map<String, Object> map) {

        return jobInfoMapper.query(map);
    }

    @Override
    public int save(JobInfo jobInfo) {

        return jobInfoMapper.save(jobInfo);
    }

    @Override
    public int updateSelective(Map<String, Object> map) {

        return jobInfoMapper.updateSelective(map);
    }
}
