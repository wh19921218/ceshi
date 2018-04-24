package com.cn.lianshou.mapper;

import com.cn.lianshou.entity.JobInfo;

import java.util.List;
import java.util.Map;

/**
 * Author: Wanghh
 * Date: 2018/4/13 14:20
 */
public interface JobInfoMapper {

    /**
     * 查询  定时任务信息
     * @param map
     * @return
     */
    List<JobInfo> query(Map<String, Object> map);

    /**
     * 保存  定时任务信息
     * @param jobInfo
     * @return
     */
    int save(JobInfo jobInfo);

    /**
     * 修改  定时任务信息
     * @param map
     * @return
     */
    int updateSelective(Map<String, Object> map);
}
