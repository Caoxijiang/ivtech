package com.ivtech.qaii.service.impl;

import com.ivtech.qaii.mapper.SysRolePermissionMapper;
import com.ivtech.qaii.pojo.SysRolePermission;
import com.ivtech.qaii.service.SysRolePermissionService;
import com.ivtech.qaii.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Override
    public Integer save(List<SysRolePermission> s) {
        return sysRolePermissionMapper.save(s);
    }

    @Override
    public List<SysRolePermission> findidBypidAndrid(List<SysRolePermission> list) {
        return sysRolePermissionMapper.findidBypidAndrid(list);
    }
}
