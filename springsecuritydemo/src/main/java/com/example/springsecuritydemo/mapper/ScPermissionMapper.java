package com.example.springsecuritydemo.mapper;

import com.example.springsecuritydemo.model.ScPermission;
import org.springframework.stereotype.Repository;

@Repository
public interface ScPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScPermission record);

    int insertSelective(ScPermission record);

    ScPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScPermission record);

    int updateByPrimaryKey(ScPermission record);
}