package com.yang.blog.service;

import com.yang.blog.dao.BlogRepository;
import com.yang.blog.po.Blog;
import com.yang.blog.po.Type;
import com.yang.blog.util.MyBeanUtils;
import com.yang.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository repository;
    @Override
    public Blog getBlog(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return repository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!"".equals(blog.getTitle())&&blog.getTitle()!=null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId()!=null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return repository.save(blog);
    }


    @Override
    public void deleteBlog(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b=repository.getOne(id);
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return repository.save(b);
    }

    @Override
    public List<Blog> listBlogByYear(int year) {
        return null;
    }
    //    @Override
//    public List<Blog> listBlogByYear(int year) {
//        long time=new Date(2019,0,0).getTime();
//        long time2=new Date(2020,0,0).getTime();
//        List<Blog> list=new ArrayList<>();
//        for(Blog blog:repository.findAll()){
//            if(blog.getCreateTime().getTime()>time&&blog.getCreateTime().getTime()<time2){
//                list.add(blog);
//            }
//        }
//        return list;
//    }


    @Override
    public List<Blog> listBlog() {
        return repository.findAll();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }


}
