package com.example.springsecuritydemo.mapper;

import com.example.springsecuritydemo.model.ScRolePermission;
import org.springframework.stereotype.Repository;

@Repository
public interface ScRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScRolePermission record);

    int insertSelective(ScRolePermission record);

    ScRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScRolePermission record);

    int updateByPrimaryKey(ScRolePermission record);
}