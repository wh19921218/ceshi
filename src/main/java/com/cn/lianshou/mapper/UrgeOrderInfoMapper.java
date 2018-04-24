package com.cn.lianshou.mapper;

import com.cn.lianshou.entity.UrgeOrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.mapper.UrgeOrderInfoMapper.java
 * Author: Wanghh
 * Date: 2018/3/23 16:06
 */
@Repository
public interface UrgeOrderInfoMapper {

    /**
     * 获取所有的催收订单
     * @return
     */
    List<UrgeOrderInfo> selectAll(Map<String, Object> map);

    /**
     * 查询所有待还款订单
     * @return
     */
    List<UrgeOrderInfo> selectWaitRepayOrderList();

    /**
     * 插入订单
     * @param urgeOrderInfo
     * @return
     */
    int insert(UrgeOrderInfo urgeOrderInfo);

    /**
     * 根据状态查询  催收订单
     * @param state
     * @return
     */
    List<UrgeOrderInfo> selectByState(@Param(value = "state") Integer state);

    /**
     * 修改订单信息
     * @param map
     * @return
     */
    int updateSelective(Map<String, Object> map);
}
