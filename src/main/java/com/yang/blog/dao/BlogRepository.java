package com.yang.blog.dao;

import com.yang.blog.po.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    @Query(value = "select * from t_blog  order by id desc limit 0,?1",nativeQuery=true)
    List<Blog> findTop(int limit);


}
