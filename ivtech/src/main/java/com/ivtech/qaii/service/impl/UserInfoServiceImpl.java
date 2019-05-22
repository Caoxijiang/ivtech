package com.ivtech.qaii.service.impl;

import com.ivtech.qaii.mapper.SysRoleMapper;
import com.ivtech.qaii.mapper.UserInfoMapper;
import com.ivtech.qaii.pojo.SysPermission;
import com.ivtech.qaii.pojo.UserInfo;
import com.ivtech.qaii.service.UserInfoService;
import com.ivtech.qaii.util.MD5Utils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author cxj
 * @date 2019-04-15
 */
@Service(value = "UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;




    @Override
    public UserInfo findByUsername(String username) {
        UserInfo ui = userInfoMapper.findByUsername(username);
        return ui;
    }

    @Override
    public Integer save(UserInfo ui) {
        String pas = MD5Utils.encodePassword(ui.getPassword(), ui.getCredentialsSalt());
        ui.setPassword(pas);
        return userInfoMapper.save(ui);
    }

    @Override
    public UserInfo findByOne(Integer uid) {
        return userInfoMapper.findByUid(uid);
    }

    @Override
    public Integer saveRoleId(UserInfo ui) {
        // TODO Auto-generated method stub
        return userInfoMapper.saveRoleId(ui);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }


    @Override
    public int updatePassword(String password, Date updateDate, String uid) {
        // TODO Auto-generated method stub
        int id = Integer.parseInt(uid);
        UserInfo ui = userInfoMapper.findByUid(id);
        String pas = MD5Utils.encodePassword(password, ui.getCredentialsSalt());
        return userInfoMapper.updatePassword(pas, updateDate, uid);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

}
