package me.highgo.back.service;

import me.highgo.back.mapper.RoleMapper;
import me.highgo.back.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RoleService.java
 *
 * @Description : 角色Service
 * @Author huangzhiwei
 * @DATE 2016/5/23
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role selectByPrimaryKey(Integer id){
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }
}
