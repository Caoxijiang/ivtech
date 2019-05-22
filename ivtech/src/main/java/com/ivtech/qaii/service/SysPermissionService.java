package com.ivtech.qaii.service;

import com.ivtech.qaii.pojo.SysPermission;
import com.ivtech.qaii.pojo.SysPermissionVo;
import java.util.List;

public interface SysPermissionService {
	
	
	 SysPermission findById(int id);
	    
	 List<SysPermissionVo> findAll();
	
	
	int save(SysPermission sp);
	
	int updatePermission(SysPermission sp); 
	
	
	SysPermission findByOne(String id);
	
	int delete(String id);
}