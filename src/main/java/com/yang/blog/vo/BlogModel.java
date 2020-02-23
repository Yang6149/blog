package com.yang.blog.vo;

import com.yang.blog.po.Tag;
import com.yang.blog.po.Type;
import com.yang.blog.po.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogModel {
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private String description;
    private Date updateTime;
    private Type type;
    private List<Tag> tags=new ArrayList<>();
    private User user;

    public BlogModel(Long id, String title, String firstPicture, Integer views, String description, Date updateTime, Type type, List<Tag> tags, User user) {
        this.id = id;
        this.title = title;
        this.firstPicture = firstPicture;
        this.views = views;
        this.description = description;
        this.updateTime = updateTime;
        this.type = type;
        this.tags = tags;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
