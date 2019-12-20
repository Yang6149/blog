package com.yang.blog.service;

import com.yang.blog.dao.TypeRepository;
import com.yang.blog.po.Type;
import com.yang.blog.web.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {

        return typeRepository.save(type);
    }


    @Transactional
    @Override
    public Type getType(Long id) {

        return typeRepository.findById(id).get();
    }


    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {

        return typeRepository.findAll(pageable);
    }


    @Transactional
    @Override
    public Type uptateType(Long id, Type type) {
        Type t= getType(id);
        if(t==null){
            throw new NotFoundException("id is not found");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }
}
