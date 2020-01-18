package com.yang.blog.dao;

import com.yang.blog.po.Comment;
import com.yang.blog.po.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
        List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
