package com.example.springsecuritydemo.mapper;

import com.example.springsecuritydemo.model.ScUser;

public interface ScUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScUser record);

    int insertSelective(ScUser record);

    ScUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScUser record);

    int updateByPrimaryKey(ScUser record);
}