package com.yang.blog.service;

import com.yang.blog.po.Comment;
import com.yang.blog.po.User;

import java.util.List;

public interface CommentService {

    public List<Comment> ListCommentByBlogId(Long blogId);
    Comment save(Comment comment);
}
