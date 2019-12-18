package com.yang.blog.service;

import com.yang.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);

}
