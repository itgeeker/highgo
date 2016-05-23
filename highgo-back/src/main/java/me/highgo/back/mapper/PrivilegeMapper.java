package me.highgo.back.mapper;

import me.highgo.back.model.Privilege;

import java.util.List;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);

    List<Privilege> selectListByRid(Integer rid);
}