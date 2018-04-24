package com.cn.lianshou.service;

import com.cn.lianshou.entity.SystemConfig;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.SystemConfigService.java
 * Author: Wanghh
 * Date: 2018/4/4 17:08
 */
public interface SystemConfigService {

    /**
     * 查询  配置信息
     * @param map
     * @return
     */
    List<SystemConfig> query(Map<String, Object> map);

    /**
     * 保存  配置信息
     * @param systemConfig
     * @return
     */
    int save(SystemConfig systemConfig);

    /**
     * 修改  配置信息
     * @param map
     * @return
     */
    int updateSelective(Map<String, Object> map);
}
