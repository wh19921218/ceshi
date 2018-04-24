package com.cn.lianshou.controllrer;

import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.entity.BorrowUserInfo;
import com.cn.lianshou.service.BorrowUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.controllrer.BorrowUserInfoController.java
 * Author: Wanghh
 * Date: 2018/3/24 14:49
 */
@Controller
public class BorrowUserInfoController {

    @Autowired
    private BorrowUserInfoService borrowUserInfoService;



    /**
     * 跳转到  借款人信息页面
     * @return
     */
    @RequestMapping(value = "/show/borrow/user/info")
    public String showBorrowUserInfo(){

        return "borrow-user-info";
    }

    /**
     * 查询用户信息   分页
     * @param page
     * @param rows
     * @param phone
     * @return
     */
    @RequestMapping(value = "/borrow/user/info/page")
    @ResponseBody
    public PageResult selectUserInfoBypage(Integer page,Integer rows, String phone, String channel){

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("page", page);
        paramMap.put("rows", rows);
        paramMap.put("phone", phone);
        paramMap.put("channel", channel);

        return borrowUserInfoService.selectAll(paramMap);
    }

    /**
     * 查询用户信息
     * @param channel
     * @param phone
     * @return
     */
    @RequestMapping(value = "/borrow/user/info")
    @ResponseBody
    public BorrowUserInfo selectUserInfoByChannelAndPhone(String channel, String phone){

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("channel", channel);
        paramMap.put("phone", phone);

        return borrowUserInfoService.selectBorrowUserInfo(paramMap);
    }






}
