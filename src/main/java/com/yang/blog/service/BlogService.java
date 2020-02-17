package com.yang.blog.service;

import com.yang.blog.po.Blog;
import com.yang.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    void deleteBlog(Long id);

    Blog updateBlog(Long id,Blog blog);

    List<Blog> listBlogByYear(int year);

    List<Blog> listBlog();

    List<Blog> listRedisBlog();

    int count();

    List<Blog> recommentBlog();

    List<Blog> newBlog(int num);
}
