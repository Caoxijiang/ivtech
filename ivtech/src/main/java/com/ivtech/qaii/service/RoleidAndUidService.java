package com.ivtech.qaii.service;

import com.ivtech.qaii.pojo.RoleidAndUid;

import java.util.List;

public interface RoleidAndUidService {
    Integer saveRoleId(List<RoleidAndUid> list);

    List<RoleidAndUid> findallByuid(RoleidAndUid uid);
}
