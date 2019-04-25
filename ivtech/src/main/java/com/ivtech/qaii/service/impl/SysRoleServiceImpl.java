package com.ivtech.qaii.service.impl;

import com.ivtech.qaii.mapper.SysRoleMapper;
import com.ivtech.qaii.pojo.SysRole;
import com.ivtech.qaii.pojo.UserInfo;
import com.ivtech.qaii.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysRoleMapper sysRoleMapper;

//	@Override
//	public Page<SysRole> findByRole(String role, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return sysRoleDao.findByRole(role, pageable);
//	}

	@Override
	public List <SysRole> findByRole(String role ) {
		// TODO Auto-generated method stub
		return sysRoleMapper.findByRole(role);
	}
//	@Override
//	public Page<SysRole> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return sysRoleDao.findAll(pageable);
//	}
	@Override
	@Transactional
	public int updateState(boolean available, String uid) {
		// TODO Auto-generated method stub
		System.out.print("hhhe--------------"+uid);
		int id = Integer.parseInt(uid);
		SysRole sr = sysRoleMapper.findById(id);
		//sysRoleMapper.delete(sr);
		return 1;
	}
	@Override
	public SysRole save(SysRole sr) {
		
		return sysRoleMapper.save(sr);
	}
	@Override
	public int updateRole(SysRole sr) {
		// TODO Auto-generated method stub
//		String description = sr.getDescription();
//		String role = sr.getRole();
//		String id = sr.getId()+"";
		return sysRoleMapper.updateRole(sr);
	}
	@Override
	public SysRole findByOne(String id) {
		int uid = Integer.parseInt(id);
		return sysRoleMapper.findById(uid);
	}


	@Override
	public List<SysRole> findAll() {
		// TODO Auto-generated method stub
		return (List<SysRole>) sysRoleMapper.findAll();
	}
}