package me.highgo.back.service;

import me.highgo.back.mapper.MenuMapper;
import me.highgo.back.model.Menu;
import me.highgo.back.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * MenuService.java
 *
 * @Description :
 * @Author huangzhiwei
 * @DATE 2016/5/23
 */
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public List<Menu> selectList(Map<String, Integer> param) {
        Integer rid = param.get("rid");
        List<Menu> menuList= menuMapper.selectByRid(rid);
        return menuList;
    }
}
