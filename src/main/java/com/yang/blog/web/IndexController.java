package com.yang.blog.web;

import com.yang.blog.po.Blog;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.TagService;
import com.yang.blog.service.TypeService;
import com.yang.blog.vo.BlogQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    //git
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",blogService.listBlog(pageable,new BlogQuery()));
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("types",typeService.listType(pageable));
        model.addAttribute("recommendBlogs",blogService.recommentBlog());
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        Blog b=blogService.getAndConvert(id);
        //Blog b=blogService.getBlog(id);
        System.out.println(b.getViews()+"after");
        System.out.println(b.getViews()+"after");
        logger.info(b.toString());
        if(b.getComments()!=null){
            model.addAttribute("comments",b.getComments());
        }
        model.addAttribute("blog",b);
        return "blog";
    }
    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.newBlog(3));
        return "_fragments :: newblogList";
    }
}
