package com.cn.lianshou.mapper;

import com.cn.lianshou.entity.BorrowUserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.mapper.BorrowUserInfoMapper.java
 * Author: Wanghh
 * Date: 2018/3/24 14:36
 */
@Repository
public interface BorrowUserInfoMapper {

    List<BorrowUserInfo> selectBorrowUserInfo(Map<String, Object> map);

    int insert(BorrowUserInfo borrowUserInfo);

}
