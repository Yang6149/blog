package com.yang.blog.service;

import com.yang.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag(String str);

    List<Tag> listTag();

    Tag uptateTag(Long id,Tag tag);

    void deleteTag(Long id);
}
