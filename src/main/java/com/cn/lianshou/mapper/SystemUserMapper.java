package com.cn.lianshou.mapper;

import com.cn.lianshou.entity.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemUserMapper {


    /**
     * 根据用户名和密码  查询用户是否存在
     * @param userName
     * @param password
     * @return
     */
    SystemUser queryUser(@Param(value = "userName") String userName,@Param(value = "password") String password);
    /*SystemUser queryUser(Map<String, Object> paramMap);*/
    /**
     * 根据用户名  查询用户是否存在
     * @param userName
     * @return
     */
    SystemUser queryUserByUserName(@Param(value = "userName") String userName);


    /**
     * 获取用户列表
     * @return
     */
    List<SystemUser> queryUserList(Map<String, Object> paramMap);

    /**
     * 新增用户
     * @param userName
     * @param password
     * @param phone
     * @param userType
     * @return
     */
    int saveUser(@Param(value = "userName") String userName,@Param(value = "realName") String realName,@Param(value = "password") String password,@Param(value = "phone") String phone,@Param(value = "userType") Integer userType);

    /**
     * 修改用户信息
     * @return
     */
    int updateSelective(Map<String, Object> paramMap);

    /**
     * 查询可用状态下的  催收员
     * @param userType
     * @param state
     * @return
     */
    List<SystemUser> queryUserByTypeAndState(@Param(value = "userType")Integer userType,@Param(value = "state") Integer state);

}