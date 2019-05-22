package com.ivtech.qaii.controller;

import com.ivtech.qaii.pojo.RoleidAndUid;
import com.ivtech.qaii.pojo.SysRole;
import com.ivtech.qaii.pojo.UserInfo;
import com.ivtech.qaii.service.RoleidAndUidService;
import com.ivtech.qaii.service.UserInfoService;
import com.ivtech.qaii.util.StateParameter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RoleidAndUidService roleidAndUidService;
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
        Integer result ;
         result = userInfoService.save(ui);
        if( result>0 ){
            SecurityUtils.getSubject().getSession().setAttribute("user", ui);
            return getModelMap(StateParameter.SUCCESS,result,"注册成功");
        }else{
            return getModelMap(StateParameter.FAULT,null,"注册失败");
        }

    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    //@RequiresPermissions("userInfo:upate")//权限管理;
    public ModelMap updatePassword(String password,String uid){
        Date updateDate = new Date();
        int result = userInfoService.updatePassword(password, updateDate, uid);
        if( result == 1 ){
            return getModelMap(StateParameter.SUCCESS,result,"操作成功");
        }else{
            return getModelMap(StateParameter.FAULT,null,"操作失败");
        }
    }


    /**
     * 用户更新
     * @return
     */
    @RequestMapping("/updateUser")
    //@RequiresPermissions("userInfo:upate")//权限管理;
    @ResponseBody
    public ModelMap updateUser(UserInfo ui){
        logger.info("更新客户："+ui.getName() );
        try {
           int res= userInfoService.updateUserInfo(ui);
           if(res>0){
               return getModelMap(StateParameter.SUCCESS,ui,"更新客户信息成功");
           }else {
               return getModelMap(StateParameter.FAULT,null,"更新客户信息失败");
           }
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT,null,"更新客户信息失败");
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
        Integer result ;
        result = userInfoService.save(ui);
        if( result!=null ){
            return getModelMap(StateParameter.SUCCESS,result,"添加客户成功");
        }else{
            return getModelMap(StateParameter.FAULT,null,"添加客户失败");
        }

    }

    /**
     * 保存权限id （用户分配角色）
     * @return
     */
    @RequestMapping("/saveRoleId")
    @ResponseBody
    public ModelMap saveRoleId(String str,Integer uid){
        logger.info("查询客户信息："+str);
        RoleidAndUid uids=new RoleidAndUid();
        uids.setUid(uid);
        try {
            String [] array = str.split("-");
            List<RoleidAndUid> list = new ArrayList<RoleidAndUid>();
            for (int i = 0; i < array.length; i++) {
                RoleidAndUid sr  = new RoleidAndUid();
                sr.setRoleId( Integer.parseInt(array[i]));
                sr.setUid(uid);
                list.add(sr);
            }


            List<RoleidAndUid> rids=roleidAndUidService.findallByuid(uids);

            for  ( int  i  =   0 ; i  <  list.size()  ; i ++ )   {
                for  ( int  j  = 0; j<rids.size() ;j ++ )   {
                    if  (list.get(i).getRoleId()==rids.get(j).getRoleId()
                            && list.get(i).getUid()==rids.get(j).getUid()
                    )   {
                        list.get(i).setId(rids.get(j).getId());
                    }
                }
            }


            Integer result = roleidAndUidService.saveRoleId(list);
            return getModelMap(StateParameter.SUCCESS,result,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT,null,"操作失败");
        }
    }


    /**
     * 通过uid查询客户拥有的角色id
     * @return
     */
    @RequestMapping("/findByRoleId")
    @ResponseBody
    public ModelMap findByRoleId(Integer uid){
        logger.info("查询客户信息："+uid);
        try {
            UserInfo result = userInfoService.findByOne(uid);
            List<SysRole> list = result.getRoleList();
            JsonConfig config = new JsonConfig();
            config.setExcludes(new String[]{"permissions","userInfos"});
            JSONArray json =JSONArray.fromObject(list,config);
            logger.info("拥有角色个数：----------------"+list.size());
            return getModelMap(StateParameter.SUCCESS,json,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT,null,"操作失败");
        }
    }


    /**
     * 通过uid查询客户信息
     * @return
     */
    @RequestMapping("/findByOne")
    @ResponseBody
    public ModelMap findByOne(Integer uid){
        logger.info("查询客户信息："+uid);
        UserInfo result = userInfoService.findByOne(uid);
        if( result != null ){
            JsonConfig config = new JsonConfig();
            config.setExcludes(new String[]{"roleList"});
            JSONObject json =JSONObject.fromObject(result,config);
            return getModelMap(StateParameter.SUCCESS,json,"操作成功");
        }else{
            return getModelMap(StateParameter.FAULT,null,"操作失败");
        }
    }





}
