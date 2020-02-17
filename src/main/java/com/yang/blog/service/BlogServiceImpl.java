package com.yang.blog.service;

import com.yang.blog.dao.BlogRepository;
import com.yang.blog.po.Blog;
import com.yang.blog.po.Type;
import com.yang.blog.util.Markdown2Html;
import com.yang.blog.util.MyBeanUtils;
import com.yang.blog.vo.BlogQuery;
import com.yang.blog.web.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private BlogRepository repository;
    @Override
    public Blog getBlog(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = repository.getOne(id);
        if(blog == null){
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();

        blog.setContent(Markdown2Html.markdownToHtmlExtensions(content));

        return blog;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        List<Object>list = redisTemplate.opsForList().range("page"+pageable.getPageNumber(),0,-1);
        Date saveTime =null;
        Date pageTime =null;
        Page<Blog> page = null;
        if(list.size()==2){
            saveTime = (Date)redisTemplate.opsForValue().get("saveTime");
            pageTime = (Date)list.get(0);
            page = (Page<Blog>) list.get(1);

        }
        if(list.size()<2||(saveTime!=null&&saveTime.compareTo(pageTime)>0)){
            synchronized(this){
                if (list.size()<2||saveTime.compareTo(pageTime)>0){
                    System.out.println("更新---------------------------------------");
                    RedisSerializer<String> serializer = new StringRedisSerializer();
                    redisTemplate.setKeySerializer(serializer);
                    page = repository.findAll(new Specification<Blog>() {
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
                    redisTemplate.delete("page"+pageable.getPageNumber());
                    redisTemplate.opsForList().rightPush("page"+pageable.getPageNumber(),new Date());
                    redisTemplate.opsForList().rightPush("page"+pageable.getPageNumber(),page);

                }
            }
        }
        return page;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        int incre=0;

        redisTemplate.opsForValue().set("saveTime",new Date());
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
    public List<Blog> listRedisBlog(){
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        List<Blog> list = (List<Blog>) redisTemplate.opsForValue().get("allList");
        if(list==null){
            redisTemplate.opsForValue().set("allList",listBlog());
        }
        return list;
    }

    @Override
    public int count() {
        return (int) repository.count();
    }

    @Override
    public List<Blog> recommentBlog() {

        return repository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),true));
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        });
    }

    @Override
    public List<Blog> newBlog(int num) {
        return repository.findTop(num);
    }


}
