package com.ivtech.qaii.common;


import com.ivtech.qaii.pojo.UserInfo;
import org.apache.shiro.SecurityUtils;

public  class PermissionUtil  {

	public static boolean isSearchAllData() {

        String isSearch = SecurityUtils.getSubject().getSession().getAttribute("isSearchAllData")+"";
		if("null".equals(isSearch)){
            System.out.println("false");
            return false;
        }else{
            System.out.println("true");
            return true;
        }
	}


    /**
     * 获取当前登录的用户角色
     * */
    public static UserInfo getCurrentUser(){

        UserInfo currentUserId = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("user");
        return currentUserId;
    }
}
