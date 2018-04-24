package com.cn.lianshou.service.Impl;

import com.cn.lianshou.entity.SystemConfig;
import com.cn.lianshou.mapper.SystemConfigMapper;
import com.cn.lianshou.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.Impl.SystemConfigService.java
 * Author: Wanghh
 * Date: 2018/4/4 17:08
 */
@Service("SystemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;


    /**
     * 查询  配置信息
     * @param map
     * @return
     */
    public List<SystemConfig> query(Map<String, Object> map) {

        return systemConfigMapper.query(map);
    }

    /**
     * 保存  配置信息
     * @param systemConfig
     * @return
     */
    public int save(SystemConfig systemConfig) {

        return systemConfigMapper.save(systemConfig);
    }

    /**
     * 修改  配置信息
     * @param map
     * @return
     */
    public int updateSelective(Map<String, Object> map){

        return systemConfigMapper.updateSelective(map);
    }
}
