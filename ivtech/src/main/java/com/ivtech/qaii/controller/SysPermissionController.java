package com.ivtech.qaii.controller;

import com.ivtech.qaii.pojo.SysPermission;
import com.ivtech.qaii.pojo.SysPermissionVo;
import com.ivtech.qaii.service.SysPermissionService;
import com.ivtech.qaii.util.StateParameter;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/sysPermission")
public class SysPermissionController extends BaseController {
	
 
	@Resource
	SysPermissionService sysPermissionService;
    /**
     * 资源查询.
     * @return
     */
    @RequestMapping("/permissionView")
    @RequiresPermissions("sysPermission:view")//权限管理;
    public String view(){
    	
    	logger.info("加载permission....");
        return "/base/permission";
    }

 
    /**
     * 查询所有资源
     * @return
     */
    @RequestMapping("/findAll")
    //@RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public JSONArray findAll() {
   
    	List<SysPermissionVo> listVo= sysPermissionService.findAll();

    	if( listVo.size() > 0 ){
			return  JSONArray.fromObject(listVo);
    	}else{
    		return new JSONArray();
    	}
       
    }
    
    
    /**
     * 资源添加;
     * @return
     */
    @RequestMapping("/sysPermissionAdd")
    //@RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public ModelMap sysPermissionAdd(SysPermission sp){
    	logger.info("添加资源："+sp.getName());
    	logger.info("添加资源parentId："+sp.getParentId());
    	int result;
    	result = sysPermissionService.save(sp);
    	if( result>0 ){
    		return getModelMap(StateParameter.SUCCESS,result,"添加资源成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"添加资源失败");
    	}
       
    }



 
    
    
    /**
     * 资源更新
     * @return
     */
    @RequestMapping("/updatePermission")
    //@RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public ModelMap updatePermission(SysPermission sp){
    	logger.info("更新资源："+  sp.getName());
    
    	int result = sysPermissionService.updatePermission(sp);
    	if( result > 0 ){
    		return getModelMap(StateParameter.SUCCESS,result,"更新资源成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"更新资源失败");
    	}
       
    }
    
    
    /**
     * 通过uid查询资源信息
     * @return
     */
    @RequestMapping("/findByOne")
    @ResponseBody
    public ModelMap findByOne(String id){
    	logger.info("查询资源信息："+id);
    	SysPermission result = sysPermissionService.findByOne(id);
    	if( result != null ){
    		JsonConfig config = new JsonConfig(); 
    		config.setExcludes(new String[]{"roles"}); 
    		JSONObject json =JSONObject.fromObject(result,config);
    		return getModelMap(StateParameter.SUCCESS,json,"操作成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"操作失败");
    	}
    }
    
    
    
    /**
     * 通过id删除资源信息
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ModelMap delete(String id){
    	logger.info("删除资源信息："+id);
    	try {
    		sysPermissionService.delete(id);
    		return getModelMap(StateParameter.SUCCESS,null,"操作成功");
		} catch (Exception e) {
			return getModelMap(StateParameter.FAULT,null,"操作失败");
			// TODO: handle exception
		}
    }
}