package com.jim.sprjfx.service;


import com.github.pagehelper.PageInfo;
import com.jim.sprjfx.entity.User;
import com.jim.sprjfx.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// Alt + Enter 自动生成
@Slf4j
@Service    // 交由 SpringBoot 容器管理
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public boolean newUser(User user) {
        log.info("New User\t"+user.getNickname()+"\tAdd Successfully!");
        return userMapper.newUser(user) > 0 ? true : false ;
    }

}
