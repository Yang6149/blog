package com.yang.blog.service;

import com.yang.blog.dao.TagRepository;
import com.yang.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    /**
     * 获取Repository
     */
    @Autowired
    private TagRepository tagRepository;

    /**
     * 保存Tag
     * @param tag
     * @return
     */
    @Override
    public Tag saveTag(Tag tag) {

        return tagRepository.save(tag);
    }

    /**
     * 根据id获得Tag
     * @param id
     * @return
     */
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    /**
     * 获得当前Page的Tag
     * @param pageable
     * @return
     */
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag(String str) {
        return tagRepository.findAllById(strConver2List(str));
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    private List<Long> strConver2List(String str){
        List<Long> list=new ArrayList<>();
        String tags[]=str.split(",");
        for(String i:tags){
            list.add(Long.parseLong(i));
        }

        return list;
    }


    /**
     * Update Tag
     * @param id
     * @param tag
     * @return
     */
    @Override
    public Tag uptateTag(Long id, Tag tag) {
        Tag t=getTag(id);
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
