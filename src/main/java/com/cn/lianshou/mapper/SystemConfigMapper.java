package com.cn.lianshou.mapper;

import com.cn.lianshou.entity.SystemConfig;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.mapper.SystemConfigMapper.java
 * Author: Wanghh
 * Date: 2018/4/4 16:10
 */
public interface SystemConfigMapper {

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
