package com.yang.blog.web;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){

        //int a=9/0;
//        String blog=null;
//        if(blog==null){
//            throw new NotFoundException("blog not found");
//        }
        System.out.println("print-----------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
}
