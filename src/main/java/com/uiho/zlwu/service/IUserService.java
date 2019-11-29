package com.uiho.zlwu.service;

import com.uiho.zlwu.model.User;

import java.util.List;

public interface IUserService {

    public User findUser(Integer id);

    public User selectUserByAccount(String account);

    List<User> findUsers();
}
