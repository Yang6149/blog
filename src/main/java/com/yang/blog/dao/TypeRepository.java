package com.yang.blog.dao;

import com.yang.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TypeRepository extends JpaRepository<Type,Long> {
    //@Override
    //Type findById(Long id);


}
