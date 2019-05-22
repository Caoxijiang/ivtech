package com.ivtech.qaii.mapper;

import com.ivtech.qaii.pojo.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermissionMapper {
    int save(List<SysRolePermission> record);

    int insertSelective(SysRolePermission record);

    List<SysRolePermission> findidBypidAndrid(List<SysRolePermission> list);
}