package com.uiho.zlwu.service;

import java.util.Set;

/**
 * @Author wangyong
 * @Date 2019/11/27 11:18
 */
public interface IPermissionService {

    Set<String> selectPermissions(String account);
}
