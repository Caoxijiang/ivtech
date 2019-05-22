package com.ivtech.qaii.service.impl;
import com.ivtech.qaii.mapper.SysPermissionMapper;
import com.ivtech.qaii.pojo.SysPermission;
import com.ivtech.qaii.pojo.SysPermissionVo;
import com.ivtech.qaii.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysPermissionMapper sysPermissionMapper;
	@Override
	public SysPermission findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SysPermissionVo> findAll() {
		List<SysPermission> list= (List<SysPermission>) sysPermissionMapper.findAll();
		List<SysPermissionVo> listVo = new ArrayList<SysPermissionVo>();

		SysPermissionVo root = new SysPermissionVo();
		root.setId(0);
		root.setParent("#");
		root.setText("资源根节点");
		root.setState(true);
		listVo.add(root);
		for (SysPermission sysPermission : list) {
			SysPermissionVo VO = new SysPermissionVo();
			VO.setId(sysPermission.getId());
			VO.setParent(sysPermission.getParentId()+"");
			VO.setText(sysPermission.getName());
			VO.setState(true);
			listVo.add(VO);
		}
		return listVo;
	}

	@Override
	public int save(SysPermission sp) {
		// TODO Auto-generated method stub
		return sysPermissionMapper.save(sp);
	}
	@Override
	public int updatePermission(SysPermission sp) {
//		String name = sp.getName();
//		String  resourceType = sp.getResourceType();
//		String url = sp.getUrl();
//		String  permission = sp.getPermission();
//		String id = sp.getId()+"";
//		boolean state = sp.getAvailable();
		System.out.println(sp.getResourceType()+"-------------------"+sp.getId());
		System.out.println(sp.getPermission()+"-------------------"+sp.getUrl());
		return sysPermissionMapper.updatePermission(sp);
	}
	@Override
	public SysPermission findByOne(String id) {
		int idInt = Integer.parseInt(id);
		return sysPermissionMapper.findById(idInt);
	}
	@Override
	public int delete(String id) {
		int idInt = Integer.parseInt(id);
		return sysPermissionMapper.delete(idInt);
	}


}