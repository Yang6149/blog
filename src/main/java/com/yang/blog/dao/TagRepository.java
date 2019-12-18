package com.yang.blog.dao;

import com.yang.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag,Long> {
}
