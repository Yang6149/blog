package com.yang.blog.web.admin;

import com.yang.blog.po.Blog;
import com.yang.blog.po.User;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.TagService;
import com.yang.blog.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class BlogController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,Blog blog, Model model){
        System.out.println(blogService.listBlog(pageable,blog).getContent());
        model.addAttribute("types",typeService.listType(pageable));
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        logger.info("访问list");
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,Blog blog, Model model){
        System.out.println(blogService.listBlog(pageable,blog).getContent());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        logger.info("查找blogs");
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model,Pageable pageable){
        model.addAttribute("blog",new Blog());
        model.addAttribute("tags",tagService.listTag(pageable).getContent());
        model.addAttribute("types",typeService.listType(pageable).getContent());
        logger.info("增加blog");
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(@Valid Blog blog, BindingResult result, RedirectAttributes attributes,HttpSession session){

        if(result.hasErrors()){
            logger.warn("result.hasErrors");
            logger.warn(blog.getTags().toString());
            logger.warn(result.getFieldErrors().toString());
            return "admin/blogs-input";
        }

        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        blog.setUpdateTime(new Date());
        blog.setUser((User) session.getAttribute("user"));
        Blog b=blogService.saveBlog(blog);


        if(b!=null){
            attributes.addFlashAttribute("message","操作成功");
        }else{
            attributes.addFlashAttribute("message","操作失败");
        }
        logger.info("提交增加blog");
        return "redirect:/admin/blogs";

    }
    @GetMapping("/blogs/{id}/input")
    public String update(Model model,Pageable pageable,@PathVariable("id") Long id){
        model.addAttribute("blog",blogService.getBlog(id));
        model.addAttribute("tags",tagService.listTag(pageable).getContent());
        model.addAttribute("types",typeService.listType(pageable).getContent());
        logger.info("修改blog");
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        blogService.deleteBlog(id);

        return "redirect:/admin/blogs";
    }

}
