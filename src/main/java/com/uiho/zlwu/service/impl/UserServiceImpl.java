package com.uiho.zlwu.service.impl;

import com.uiho.zlwu.dao.UserMapper;
import com.uiho.zlwu.model.User;
import com.uiho.zlwu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User findUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectUserByAccount(String account) {
        return userMapper.selectUserByAccount(account);
    }

    @Override
    public List<User> findUsers() {
        return userMapper.selectAll();
    }
}
