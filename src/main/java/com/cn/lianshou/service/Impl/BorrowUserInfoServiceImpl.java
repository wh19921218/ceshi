package com.cn.lianshou.service.Impl;

import com.cn.lianshou.common.util.pagehelper.PageInfo;
import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.mapper.BorrowUserInfoMapper;
import com.cn.lianshou.entity.BorrowUserInfo;
import com.cn.lianshou.service.BorrowUserInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.Impl.BorrowUserInfoServiceImpl.java
 * Author: Wanghh
 * Date: 2018/3/24 14:44
 */
@Service
public class BorrowUserInfoServiceImpl implements BorrowUserInfoService {

    @Autowired
    private BorrowUserInfoMapper borrowUserInfoMapper;


    /**
     * 查询  分页
     * @param paramMap
     * @return
     */
    public PageResult selectAll(Map<String, Object> paramMap) {

        PageHelper.startPage(Integer.parseInt(paramMap.get("page").toString()), Integer.parseInt(paramMap.get("rows").toString()));

        paramMap.remove("page");
        paramMap.remove("rows");

        List<BorrowUserInfo> borrowUserInfoList = borrowUserInfoMapper.selectBorrowUserInfo(paramMap);

        PageInfo<BorrowUserInfo> pageInfo = new PageInfo<BorrowUserInfo>(borrowUserInfoList);

        PageResult pageResult = new PageResult();

        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(borrowUserInfoList);

        return pageResult;
    }

    /**
     * 查询用户信息
     * @param map
     * @return
     */
    public BorrowUserInfo selectBorrowUserInfo(Map<String, Object> map) {

        List<BorrowUserInfo> borrowUserInfoList = borrowUserInfoMapper.selectBorrowUserInfo(map);

        if (StringUtils.isEmpty(borrowUserInfoList)){

            return new BorrowUserInfo();
        } else {

            return borrowUserInfoList.get(0);
        }
    }

    /**
     * 插入借款人用户信息
     * @param borrowUserInfo
     * @return
     */
    public int insert(BorrowUserInfo borrowUserInfo) {

        return borrowUserInfoMapper.insert(borrowUserInfo);
    }


}
