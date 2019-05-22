package com.ivtech.qaii.service;

import com.ivtech.qaii.pojo.UserInfo;

import java.util.Date;
import java.util.List;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);

    Integer save(UserInfo ui);

    UserInfo findByOne(Integer uid);

    Integer saveRoleId(UserInfo ui);

    List<UserInfo> findAll();

    int updatePassword(String password, Date updateDate, String uid);

    int updateUserInfo(UserInfo userInfo);
}
