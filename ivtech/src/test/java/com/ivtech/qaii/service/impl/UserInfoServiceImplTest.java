package com.ivtech.qaii.service.impl;

import com.ivtech.qaii.pojo.UserInfo;
import com.ivtech.qaii.service.UserInfoService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class UserInfoServiceImplTest {
    @Resource
    private UserInfoService userInfoService;
    @Test
    public void findByUsername() {
//        UserInfo d=new UserInfo();
//        d.setPassword("21212");
//        int u =userInfoService.save(d);
//        System.out.println(u);
    }

    @Test
    public void save() {
        System.out.println(222);
    }
}