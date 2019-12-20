package com.yang.blog.service;

import com.yang.blog.po.Blog;
import com.yang.blog.vo.ArchiveMAP;
import com.yang.blog.vo.YearBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArchiveMapImpl implements ArchiveMap {
    ArchiveMAP map=new ArchiveMAP();
    @Autowired
    private BlogService blogService;

    @Override
    public ArchiveMAP getMap() {
        map.setArchive(new ArrayList<YearBlog>());
        List<Blog>list= blogService.listBlog();
        HashMap<Integer,List<Blog>> hashmap=new HashMap<>();
        for(Blog blog:list){
            int year= new Calendar.Builder().setInstant( blog.getCreateTime()).build().get(Calendar.YEAR);
            System.out.println(year);
            if(hashmap.get(year)==null){
                hashmap.put(year,new ArrayList<Blog>());
            }
            hashmap.get(year).add(blog);

        }
        for(Map.Entry e:hashmap.entrySet()){
            YearBlog y=new YearBlog();
            y.setKey((Integer) e.getKey());

            y.setValue((List<Blog>) e.getValue());
            map.getArchive().add(y);
        }
        return map;
    }
}
