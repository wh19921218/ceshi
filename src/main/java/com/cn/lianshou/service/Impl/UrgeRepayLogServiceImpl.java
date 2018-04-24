package com.cn.lianshou.service.Impl;

import com.cn.lianshou.mapper.UrgeRepayLogMapper;
import com.cn.lianshou.entity.UrgeRepayLog;
import com.cn.lianshou.service.UrgeRepayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName: com.cn.lianshou.service.Impl.UrgeRepayLogServiceImpl.java
 * Author: Wanghh
 * Date: 2018/3/31 17:50
 */
@Service
public class UrgeRepayLogServiceImpl implements UrgeRepayLogService {

    @Autowired
    private UrgeRepayLogMapper urgeRepayLogMapper;

    /**
     * 保存 催收记录
     * @param urgeRepayLog
     * @return
     */
    public int insert(UrgeRepayLog urgeRepayLog) {

        return urgeRepayLogMapper.insert(urgeRepayLog);
    }
}
