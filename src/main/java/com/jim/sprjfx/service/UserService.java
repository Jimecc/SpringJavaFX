package com.jim.sprjfx.service;

import com.jim.sprjfx.entity.User;

import java.util.List;


public interface UserService {

    // 查询所有用户
    public List<User> listUsers();

    // 按用户名查找用户
    public User getUserByUserName(String userName);

    // 添加用户
    public boolean newUser(User user);

}
