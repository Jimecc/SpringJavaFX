package com.jim.sprjfx.mapper;

import com.jim.sprjfx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    // 查询所有用户
    public List<User> listUsers();

    // 根据用户名查询用户
    public User getUserByUserName(String userName);

    // 添加用户
    public int newUser(User user);
}
