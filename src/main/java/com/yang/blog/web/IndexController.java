package com.yang.blog.web;

import com.yang.blog.po.Blog;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.TagService;
import com.yang.blog.service.TypeService;
import com.yang.blog.vo.BlogQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger= LoggerFactory.getLogger(this.getClass());
    //git
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/")
    public String index(Model model, Pageable pageable){
        model.addAttribute("page",blogService.listBlog(pageable,new BlogQuery()));
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("types",typeService.listType());
        //int a=9/0;
//        String blog=null;
//        if(blog==null){
//            throw new NotFoundException("blog not found");
//        }
        System.out.println("print-----------");
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        Blog b=blogService.getBlog(id);
        b.setViews(b.getViews());
        logger.info(b.toString());
        if(b.getComments()!=null){
            model.addAttribute("comments",b.getComments());
        }
        model.addAttribute("blog",b);
        return "blog";
    }
}
