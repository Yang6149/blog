package com.yang.blog.web;

import com.yang.blog.po.Blog;
import com.yang.blog.po.Type;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.TypeService;
import com.yang.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TypeSearchController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public String tags(Model model,@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable){

        model.addAttribute("page",blogService.listBlog(pageable,new BlogQuery()));
        model.addAttribute("types",typeService.listType());

        return "types";
    }

    @GetMapping("/types/{id}")
    public String tags(@PathVariable Long id, Model model,@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable){

        List<Blog> blogs = typeService.getType(id).getBlogs();
        List<Blog> blogRes = new ArrayList<>();
        for(int i = blogs.size()-1;i>=0;i--){
            blogRes.add(blogs.get(i));
        }
        model.addAttribute("page",new PageImpl<Blog>(blogRes));

        model.addAttribute("types",typeService.listType());

        model.addAttribute("avtiveTypeId",id);
        return "types";
    }
}
