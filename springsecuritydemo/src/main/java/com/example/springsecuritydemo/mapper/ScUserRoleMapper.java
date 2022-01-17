package com.example.springsecuritydemo.mapper;

import com.example.springsecuritydemo.model.ScUserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface ScUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScUserRole record);

    int insertSelective(ScUserRole record);

    ScUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScUserRole record);

    int updateByPrimaryKey(ScUserRole record);
}