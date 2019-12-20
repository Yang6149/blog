package com.yang.blog.vo;

import com.yang.blog.po.Blog;

import java.util.List;

public class YearBlog {
    private int key;
    private List<Blog> value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public List<Blog> getValue() {
        return value;
    }

    public void setValue(List<Blog> value) {
        this.value = value;
    }
}
