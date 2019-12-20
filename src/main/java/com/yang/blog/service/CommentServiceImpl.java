package com.yang.blog.service;

import com.yang.blog.dao.CommentRepository;
import com.yang.blog.dao.UserRepository;
import com.yang.blog.po.Comment;
import com.yang.blog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
