package com.ivtech.qaii.mapper;

import com.ivtech.qaii.pojo.SysRole;

import java.util.List;
public interface SysRoleMapper {

    int deleteByPrimaryKey(Integer id);

    Integer save(SysRole record);


    int insertSelective( SysRole record);

    SysRole findById(Integer id);

    List<SysRole> findAll();

    List< SysRole> findByRole(String role);

    int updateByPrimaryKeySelective(SysRole record);

    int updateRole(SysRole record);

    int updateState(SysRole record );
}