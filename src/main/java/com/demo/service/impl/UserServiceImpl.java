package com.demo.service.impl;


import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(User user) {
        return userMapper.getUserByUserNameAndPassword(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int delete(Integer id) {
        int delete = userMapper.delete(id);
        return delete;
    }

    @Override
    public int updateUser(User user) {
       return userMapper.updateUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public PageInfo<User> getUserPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 4);
        //查询所有的员工信息
        List<User> list = userMapper.getAllUser();
        //获取分页相关数据
        PageInfo<User> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public Map<String, Object> getUsername(String username) {
        User user = userMapper.getUsername(username);
        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            result.put("code",200);
            result.put("msg","用户名可用");
        }else {
            result.put("code",500);
            result.put("msg","用户名已存在");
        }
        return result;
    }


}
