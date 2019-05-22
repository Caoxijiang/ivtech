package com.ivtech.qaii.service;

import com.ivtech.qaii.pojo.SysRolePermission;

import java.util.List;


public interface SysRolePermissionService {

    Integer save(List<SysRolePermission> s);

    List<SysRolePermission> findidBypidAndrid(List<SysRolePermission> list);
}
