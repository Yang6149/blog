package com.yang.blog.service;

import com.yang.blog.dao.TagRepository;
import com.yang.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        return t;
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
