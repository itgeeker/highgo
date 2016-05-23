package me.highgo.back.mapper;

import me.highgo.back.model.Menu;
import me.highgo.back.model.MenuItem;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectByRid(Integer rid);
}