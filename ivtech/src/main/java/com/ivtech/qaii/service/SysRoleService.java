package com.ivtech.qaii.service;
import com.github.pagehelper.Page;
import com.ivtech.qaii.pojo.SysRole;
import com.ivtech.qaii.pojo.UserInfo;


import java.util.List;

public interface SysRoleService {
	


	List <SysRole> findByRole(String role);

	//SysRole findAll();

	//Page<SysRole> findByRole(String role, Pageable pageable);

	//Page<SysRole> findAll(Pageable pageable);
	
	public int updateState(boolean available, String id);
	
	public int updateRole(SysRole sr);
	
	SysRole save(SysRole sr);
	    
    
    public SysRole findByOne(String id);
    
    List<SysRole> findAll();

}