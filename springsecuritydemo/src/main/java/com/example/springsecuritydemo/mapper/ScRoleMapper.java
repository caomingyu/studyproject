package com.example.springsecuritydemo.mapper;

import com.example.springsecuritydemo.model.ScRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScRole record);

    int insertSelective(ScRole record);

    ScRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScRole record);

    int updateByPrimaryKey(ScRole record);

    List<ScRole> selectRoleByUserId(Integer userId);
}