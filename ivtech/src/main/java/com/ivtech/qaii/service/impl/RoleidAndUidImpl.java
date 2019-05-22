package com.ivtech.qaii.service.impl;

import com.ivtech.qaii.mapper.RoleidAndUidMapper;
import com.ivtech.qaii.pojo.RoleidAndUid;
import com.ivtech.qaii.service.RoleidAndUidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleidAndUidImpl  implements RoleidAndUidService {
    @Resource
    private RoleidAndUidMapper roleidAndUidMapper;

    @Override
    public Integer saveRoleId(List<RoleidAndUid> list) {
        return roleidAndUidMapper.saveRoleId(list);
    }

    @Override
    public List<RoleidAndUid>  findallByuid(RoleidAndUid uid) {
        return roleidAndUidMapper.findallByuid(uid);
    }
}
