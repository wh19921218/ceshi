package com.cn.lianshou.service;

import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.entity.SystemUser;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.SystemUserService.java
 * Author: Wanghh
 * Date: 2018/3/22 13:57
 */
public interface SystemUserService {

    /**
     * 根据用户名和密码  查询用户是否存在
     * @param userName
     * @param password
     * @return
     */
    SystemUser queryInfoByUserNameAndPassword(String userName,String password);


    /**
     * 根据用户名  查询用户是否存在
     * @param userName
     * @return
     */
    SystemUser queryInfoByUserName(String userName);

    /**
     * 获取所有用户
     * @param page
     * @param rows
     * @return
     */
   /* PageResult queryUserList(int page, int rows,String phone,String userType,String state);*/

    /**
     * 查询用户信息  根据条件
     * @param map
     * @return
     */
    List<SystemUser> queryUserList(Map<String,Object> map);

    /**
     * 新增用户
     * @param userName
     * @param password
     * @param phone
     * @param userType
     * @return
     */
    int saveUser(String userName,String realName,String password,String phone,Integer userType);

    /**
     * 修改用户信息
     * @return
     */
    int updateSelective(Map<String, Object> paramMap);

}
