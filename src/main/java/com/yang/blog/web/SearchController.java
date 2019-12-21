package com.yang.blog.web;

import com.yang.blog.po.Blog;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.TagService;
import com.yang.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;
    @PostMapping("/search")
    public String search(@RequestParam String query, Model model, Pageable pageable){
        BlogQuery b=new BlogQuery();
        b.setTitle(query);
        model.addAttribute("page",blogService.listBlog(pageable,b));
        model.addAttribute("tags",tagService.listTag(pageable));
        System.out.println(query);
        return "search";
    }

}
