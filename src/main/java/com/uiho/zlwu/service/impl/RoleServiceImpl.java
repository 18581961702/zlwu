package com.uiho.zlwu.service.impl;

import com.uiho.zlwu.dao.RoleMapper;
import com.uiho.zlwu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author wangyong
 * @Date 2019/11/27 11:09
 */
@Service("roleservice")
public class RoleServiceImpl implements IRoleService {

    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Override
    public Set<String> selectRoles(String account) {
        return null;
    }
}
