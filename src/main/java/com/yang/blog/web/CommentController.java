package com.yang.blog.web;

import com.yang.blog.po.Blog;
import com.yang.blog.po.Comment;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @PostMapping("/comments")
    public String comment(Comment comment, Model model){
        Blog b=blogService.getBlog(comment.getBlog().getId());
        if(comment.getParentComment().getId()==-1){
            comment.setParentComment(null);
        }
        model.addAttribute("comments",b.getComments());
        commentService.save(comment);
        //save comment get blog
        return "blog :: commentList";
    }
}
