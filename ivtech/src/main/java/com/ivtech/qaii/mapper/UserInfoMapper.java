package com.ivtech.qaii.mapper;

import com.ivtech.qaii.pojo.SysRole;
import com.ivtech.qaii.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer uid);

    Integer save(UserInfo record);

    Integer saveRoleId(@Param("roles") UserInfo roles);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateUserInfo(UserInfo record);

    UserInfo findByUsername(String name);

    UserInfo findByUid(Integer uid);

    List<UserInfo> findAll();

    int updatePassword(@Param("password") String password,
                       @Param("updateDate")Date updateDate,
                       @Param("uid") String uid);

}