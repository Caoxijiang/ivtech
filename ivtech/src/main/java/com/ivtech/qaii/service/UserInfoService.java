package com.ivtech.qaii.service;

import com.ivtech.qaii.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);

    UserInfo save(UserInfo ui);

    UserInfo findByOne(Integer uid);

    UserInfo saveRoleId(UserInfo ui);

    List<UserInfo> findAll();
}
