package com.paiBuy.dao.mapper;

import org.springframework.stereotype.Component;

import com.paiBuy.bean.User;

@Component
public interface UserMapper {
     public  void saveUser(User user);
}
