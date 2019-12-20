package com.yang.blog.web;

import com.yang.blog.service.BlogService;
import com.yang.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    //git
    @Autowired
    private BlogService blogService;
    @GetMapping("/")
    public String index(Model model, Pageable pageable){
        model.addAttribute("page",blogService.getBlog((long) 26));

        //int a=9/0;
//        String blog=null;
//        if(blog==null){
//            throw new NotFoundException("blog not found");
//        }
        System.out.println("print-----------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog(Model model){
        model.addAttribute("page",blogService.getBlog((long) 26));
        return "blog";
    }
}
