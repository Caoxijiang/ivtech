package com.ivtech.qaii.pojo;

import java.util.List;

public class SysRole {
    private Integer id;

    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    private String description;// 角色描述,UI界面显示使用

    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:

    private List<SysPermission> permissions;    //角色 -- 权限关系：多对多关系;

    private List<UserInfo> userInfos;// 一个角色对应多个用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}