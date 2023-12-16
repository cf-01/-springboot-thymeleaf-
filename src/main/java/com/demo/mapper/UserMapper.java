package com.demo.mapper;


import com.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserByUserNameAndPassword(User user);

    int addUser(User user);

    int delete(Integer id);

    int updateUser(User user);

    User getUserById(Integer id);

    List<User> getAllUser();

    User getUsername(@Param("username") String username);

}
