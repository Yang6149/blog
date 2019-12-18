package com.yang.blog.service;

import com.yang.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type uptateType(Long id,Type type);

    void deleteType(Long id);
}
