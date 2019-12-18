package com.yang.blog.web.admin;

import com.yang.blog.po.User;
import com.yang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private UserService userService;
    @GetMapping("/blogs")
    public String blogs(HttpSession session){


        return "admin/blogs";
    }

}
