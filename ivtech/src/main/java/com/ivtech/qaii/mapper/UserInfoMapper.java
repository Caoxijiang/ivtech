package com.ivtech.qaii.mapper;

import com.ivtech.qaii.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer uid);

    UserInfo save(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo findByUsername(String name);

    UserInfo findByUid(Integer uid);

    List<UserInfo> findAll();
}