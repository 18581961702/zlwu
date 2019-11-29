package com.uiho.zlwu.service;

import java.util.Set;

/**
 * @Author wangyong
 * @Date 2019/11/27 11:09
 */
public interface IRoleService {

    Set<String> selectRoles(String account);
}
