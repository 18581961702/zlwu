package com.uiho.zlwu.service.impl;

import com.uiho.zlwu.dao.PermissionMapper;
import com.uiho.zlwu.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author wangyong
 * @Date 2019/11/27 11:18
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired(required = false)
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> selectPermissions(String account) {
        Set<String> set = new HashSet<>();
        set.add("user:list");
        set.add("user:edit");
        set.add("user:delete");
        return set;
    }
}
