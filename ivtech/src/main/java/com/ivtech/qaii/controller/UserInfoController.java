package com.ivtech.qaii.controller;

import com.ivtech.qaii.pojo.SysRole;
import com.ivtech.qaii.pojo.UserInfo;
import com.ivtech.qaii.service.UserInfoService;
import com.ivtech.qaii.util.StateParameter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    @Resource
    private UserInfoService userInfoService;
    /**
     * 用户注册;
     * @return
     */
    @RequestMapping(value="/regest",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap regest(UserInfo ui){
        logger.info("客户注册："+ui.getName() );
        UserInfo uis = userInfoService.findByUsername(ui.getUsername());
        if(uis!=null){
            return getModelMap(StateParameter.FAULT,"","注册失败，该账号已存在");
        }
        UserInfo result = null;
         result = userInfoService.save(ui);
        if( result!=null ){
            SecurityUtils.getSubject().getSession().setAttribute("user", result);
            return getModelMap(StateParameter.SUCCESS,result,"注册成功");
        }else{
            return getModelMap(StateParameter.FAULT,null,"注册失败");
        }

    }



    /**
     * 获取用户列表
     * @param
     * @param username
     * @return
     */
    @RequestMapping(value="/userList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("userInfo:view")//权限管理;
    public ModelMap getList( String username){
        logger.info("权限管理列表："+username);
        ModelMap map= new ModelMap();
        List<UserInfo> pageRe=null;
        try {
            if("".equals(username)){
                pageRe= userInfoService.findAll();
            }else{
               // pageRe= userInfoService.findByUserName(username);
            }
            JsonConfig config = new JsonConfig();
            //忽视loginPermissionInfo、roleInfoList数组【不添加此项json解析会报错】
            config.setExcludes(new String[]{"roleList"});
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
     * 用户查询.
     * @return
     */
    @RequestMapping("/userView")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String view(){

        logger.info("加载userInfo....");
        return "/base/userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public ModelMap userInfoAdd(UserInfo ui){
        logger.info("添加客户："+ui.getName() );
        UserInfo result = null;
        result = userInfoService.save(ui);
        if( result!=null ){
            return getModelMap(StateParameter.SUCCESS,result,"添加客户成功");
        }else{
            return getModelMap(StateParameter.FAULT,null,"添加客户失败");
        }

    }

    /**
     * 保存权限id
     * @return
     */
    @RequestMapping("/saveRoleId")
    @ResponseBody
    public ModelMap saveRoleId(String str,Integer uid){
        logger.info("查询客户信息："+str);
        try {
            String [] array = str.split("-");
            List<SysRole> list = new ArrayList<SysRole>();
            for (int i = 0; i < array.length; i++) {
                SysRole sr  = new SysRole();
                sr.setId( Integer.parseInt(array[i]) );
                list.add(sr);
            }
            UserInfo ui = userInfoService.findByOne(uid);
            ui.setRoleList(list);
            UserInfo result = userInfoService.saveRoleId(ui);
            return getModelMap(StateParameter.SUCCESS,result.getUid(),"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT,null,"操作失败");
        }
    }


}
