package com.example.springsecuritydemo.mapper;

import com.example.springsecuritydemo.model.ScUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScUser record);

    int insertSelective(ScUser record);

    ScUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScUser record);

    int updateByPrimaryKey(ScUser record);

    List<ScUser> selectList(ScUser record);
}