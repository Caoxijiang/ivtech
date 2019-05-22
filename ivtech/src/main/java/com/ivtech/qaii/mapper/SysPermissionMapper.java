package com.ivtech.qaii.mapper;

import com.ivtech.qaii.pojo.SysPermission;
import com.ivtech.qaii.pojo.SysPermissionVo;

import java.util.List;

public interface SysPermissionMapper {
    int delete(Integer id);

    int save(SysPermission str);

    int insertSelective(SysPermission record);

    SysPermission findById(Integer id);

    List<SysPermission> findAll();

    int updateByPrimaryKeySelective(SysPermission record);

    int updatePermission(SysPermission record);

}