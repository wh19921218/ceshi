package com.cn.lianshou.service;

import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.entity.BorrowUserInfo;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.BorrowUserInfoService.java
 * Author: Wanghh
 * Date: 2018/3/24 14:43
 */
public interface BorrowUserInfoService {

    PageResult selectAll(Map<String, Object> paramMap);


    BorrowUserInfo selectBorrowUserInfo(Map<String, Object> map);

    int insert(BorrowUserInfo borrowUserInfo);
}
