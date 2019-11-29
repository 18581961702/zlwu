package com.uiho.zlwu.dao;

import com.uiho.zlwu.model.UserRole;
import java.util.List;

/**
 * @author UIHO-N2
 */
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
}