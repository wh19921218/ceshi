package com.cn.lianshou.service.Impl;

import com.cn.lianshou.common.util.MD5;
import com.cn.lianshou.common.util.pagehelper.PageInfo;
import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.mapper.SystemUserMapper;
import com.cn.lianshou.entity.SystemUser;
import com.cn.lianshou.service.SystemUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.Impl.SystemUserServiceImpl.java
 * Author: Wanghh
 * Date: 2018/3/22 13:56
 */
@Service("systemConfigService")
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 根据用户名和密码  查询用户是否存在
     * @param userName
     * @param password
     * @return
     */
    public SystemUser queryInfoByUserNameAndPassword(String userName, String password) {

        password = MD5.MD5(password);
        return systemUserMapper.queryUser(userName,password);
    }

    /**
     * 根据用户名  查询用户是否存在
     * @param userName
     * @return
     */
    public SystemUser queryInfoByUserName(String userName) {

        return systemUserMapper.queryUserByUserName(userName);
    }

    /**
     * 获取所有用户信息
     * @param page
     * @param rows
     * @return
     */
    /*public PageResult queryUserList(int page, int rows,String phone,String userType,String state) {

        PageHelper.startPage(page,rows);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("phone",phone);
        paramMap.put("userType",userType);
        paramMap.put("state",state);

        List<SystemUser> userList = systemUserMapper.queryUserList(paramMap);

        PageInfo<SystemUser> pageInfo = new PageInfo<SystemUser>(userList);

        PageResult pageResult = new PageResult();

        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(userList);
        return pageResult;
    }*/

    /**
     * 查询用户信息  根据条件
     * @param map
     * @return
     */
    public List<SystemUser> queryUserList(Map<String, Object> map) {


        return systemUserMapper.queryUserList(map);
    }


    /**
     * 添加用户
     * @param userName
     * @param realName
     * @param password
     * @param phone
     * @param userType
     * @return
     */
    public int saveUser(String userName,String realName, String password, String phone, Integer userType) {

        return systemUserMapper.saveUser(userName,realName,password,phone,userType);
    }

    /**
     * 修改用户信息
     * @return
     */
    public int updateSelective(Map<String, Object> map) {

        return systemUserMapper.updateSelective(map);
    }

    /**
     * 查询可用状态下的  催收员
     * @param userType
     * @param state
     * @return
     */
    public List<SystemUser> queryUserByTypeAndState(Integer userType,Integer state){

        return systemUserMapper.queryUserByTypeAndState(userType, state);
    }
}
