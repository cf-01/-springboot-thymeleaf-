package com.demo.service;


import com.demo.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUser(User user);

    int addUser(User user);

    int delete(Integer id);

    int updateUser(User user);

    User getUserById(Integer id);

    List<User> getAllUser();

    PageInfo<User> getUserPage(Integer pageNum);

    Map<String, Object> getUsername(String username);

}
