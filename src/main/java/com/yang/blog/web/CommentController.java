package com.yang.blog.web;

import com.yang.blog.po.Blog;
import com.yang.blog.po.Comment;
import com.yang.blog.po.User;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Value("${comment.avatar}")
    private String Avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.ListCommentByBlogId(blogId));
        return "blog :: commentList";
    }
    @PostMapping("/comments")
    public String comment(Comment comment,  HttpSession session){
        Blog b=blogService.getBlog(comment.getBlog().getId());
        comment.setBlog(b);
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar("/images/avatar.png");
        }
        commentService.save(comment);
        //save comment get blog
        return "redirect:/comments/"+b.getId();
    }
}
