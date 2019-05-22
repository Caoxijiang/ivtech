package com.ivtech.qaii.mapper;

import com.ivtech.qaii.pojo.RoleidAndUid;

import java.util.List;

public interface RoleidAndUidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleidAndUid record);

    int insertSelective(RoleidAndUid record);

    RoleidAndUid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleidAndUid record);

    int updateByPrimaryKey(RoleidAndUid record);

    Integer saveRoleId(List<RoleidAndUid> list);

    List<RoleidAndUid> findallByuid(RoleidAndUid uid);

}