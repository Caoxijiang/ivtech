package com.ivtech.qaii.controller;

import com.ivtech.qaii.common.DataTablesParam;
import com.ivtech.qaii.pojo.SysPermission;
import com.ivtech.qaii.pojo.SysRole;
import com.ivtech.qaii.pojo.SysRolePermission;
import com.ivtech.qaii.service.SysPermissionService;
import com.ivtech.qaii.service.SysRolePermissionService;
import com.ivtech.qaii.service.SysRoleService;
import com.ivtech.qaii.util.StateParameter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.management.relation.RoleInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/roleInfo")
public class SysRoleController extends BaseController {
	
 
	@Resource
	SysRoleService sysRoleService;
	
	@Resource
	SysPermissionService sysPermissionService;

	@Resource
	SysRolePermissionService sysRolePermissionService;
    /**
     * 角色查询.
     * @return
     */
    @RequestMapping("/roleView")
    @RequiresPermissions("roleInfo:view")//权限管理;
    public String view(){
    	
    	logger.info("加载roleInfo....");
        return "/base/roleInfo";
    }

 
    
    /**
     * 获取角色列表
     * @param
     * @return
     */
    @RequestMapping(value="/roleList",method = RequestMethod.POST)
    @ResponseBody
    //@RequiresPermissions("userInfo:view")//权限管理;
    public ModelMap getList( SysRole role){
    	logger.info("角色管理列表："+role);
        ModelMap map= new ModelMap();
		List<SysRole>  pageRe=null;
        try {

            if("".equals(role.getRole())){
            	pageRe= sysRoleService.findAll();
            }else{
                pageRe= sysRoleService.findByRole(role.getRole());
            }
        	JsonConfig config = new JsonConfig(); 
    		config.setExcludes(new String[]{"permissions","userInfos"}); 
    		JSONArray json =JSONArray.fromObject(pageRe,config);
    		
//            map.put("sEcho", dataTablesParam.getsEcho());
//            map.put("iTotalRecords", pageRe.getTotalElements());
//            map.put("iTotalDisplayRecords",pageRe.getTotalElements());
            map.put("aaData", json );
        }catch (Exception e){
            e.printStackTrace();
        }
    	return map;
    }
    /**
     * 角色添加;
     * @return
     */
    @RequestMapping("/sysRoleAdd")
    //@RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public ModelMap sysRoleAdd(SysRole sr){
    	logger.info("添加："+sr.getRole());
    	Integer result;
		result = sysRoleService.save(sr);
    	if( result>0 ){
    		return getModelMap(StateParameter.SUCCESS,result,"添加角色成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"添加角色失败");
    	}
       
    }
    
    /**
     * 角色更新
     * @return
     */
    @RequestMapping("/updateRole")
    //@RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public ModelMap updateRole(SysRole sr){
    	logger.info("更新："+ sr.getRole() );
    	logger.info("更新："+ sr.getId());
    	logger.info("更新："+ sr.getDescription());
    	int result = sysRoleService.updateRole(sr);
    	if( result > 0 ){
    		return getModelMap(StateParameter.SUCCESS,result,"更新角色信息成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"更新角色信息失败");
    	}
       
    }

    /**
     * 角色禁用或启用;
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody
    //@RequiresPermissions("userInfo:upate")//权限管理;
    public ModelMap updateState(Boolean available, String id){
    	
    	int result = sysRoleService.updateState(available, id);
    	if( result == 1 ){
    		return getModelMap(StateParameter.SUCCESS,result,"操作成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"操作失败");
    	}
    }
    
    
    /**
     * 通过uid查询角色信息
     * @return
     */
    @RequestMapping("/findByOne")
    @ResponseBody
    public ModelMap findByOne(String id){
    	logger.info("查询角色角色信息："+id);
    	SysRole result = sysRoleService.findByOne(id);
    	if( result != null ){
    		JsonConfig config = new JsonConfig(); 
    		config.setExcludes(new String[]{"permissions","userInfos"}); 
    		JSONObject json =JSONObject.fromObject(result,config);
    		return getModelMap(StateParameter.SUCCESS,json,"操作成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"操作失败");
    	}
    }
    
    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("/findAll")
    //@RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public ModelMap findAll(String id){
   
    	List<SysRole> list= sysRoleService.findAll();
    	if( list.size() > 0 ){
    		JsonConfig config = new JsonConfig(); 
    		config.setExcludes(new String[]{"permissions","userInfos"}); 
    		JSONArray json =JSONArray.fromObject(list,config);
    		return getModelMap(StateParameter.SUCCESS,json,"查询角色信息成功");
    	}else{
    		return getModelMap(StateParameter.FAULT,null,"查询角色信息失败");
    	}
       
    }
    
    
    
    /**
     * 通过uid查询角色信息
     * @return
     */
    @RequestMapping(value="/findBypermission",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap findBypermission(String id){
    	logger.info("查询角色拥有资源："+id);
    	SysRole result = sysRoleService.findByOne(id);
    	if( result == null ){
    		return getModelMap(StateParameter.FAULT,null,"操作失败");
    	}
    	List<SysPermission> list = result.getPermissions();
    	JsonConfig config = new JsonConfig(); 
		config.setExcludes(new String[]{"roles"}); 
		JSONArray json =JSONArray.fromObject(list,config);
    	return getModelMap(StateParameter.SUCCESS,json,"操作成功");
    }
    
    

    
    /**
     * 保存角色权限资源
     * @return
     */
    @RequestMapping(value="/savePermissionId",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap savePermissionId(String id, String str){
    	logger.info("保存角色资源："+str);
    	try {
    		String [] array = str.split("-");

			SysRole srs = sysRoleService.findByOne(id);
			List<SysRolePermission> list =new ArrayList<>();
			if(srs!=null){
				for (int i = 0; i < array.length; i++) {
					SysRolePermission sr=new SysRolePermission();
					sr.setPermissionId ( Integer.parseInt(array[i]) );
					sr.setRoleId(srs.getId());
					list.add(sr);
				}

				List<SysRolePermission> idlist=sysRolePermissionService.findidBypidAndrid(list);
				for  ( int  i  =   0 ; i  <  list.size()  ; i ++ )   {
					for  ( int  j  = 0; j<idlist.size() ;j ++ )   {
						if  (list.get(i).getPermissionId()==idlist.get(j).getPermissionId()
							&& list.get(i).getRoleId()==idlist.get(j).getRoleId()
						)   {
							list.get(i).setId(idlist.get(j).getId());
						}
					}
				}
				Integer result = sysRolePermissionService.save(list);
				return getModelMap(StateParameter.SUCCESS,result,"操作成功");
			}else{
				return getModelMap(StateParameter.FAULT,null,"操作失败");
			}
				//srs.setPermissions(list);


		} catch (Exception e) {
			e.printStackTrace();
			return getModelMap(StateParameter.FAULT,null,"操作失败");
		}
    }



	/**
	 * 通过id查询角色是否可以查询所有
	 * @return
	 */
	@RequestMapping(value="/getAllData",method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("roleInfo:getAllData")//权限管理;
	public ModelMap getAllData(String id){
		logger.info("查询角色拥有资源："+id);
		return getModelMap(StateParameter.SUCCESS,null,"操作成功");
	}


}