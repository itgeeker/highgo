package me.highgo.back.service;

import me.highgo.back.mapper.PrivilegeMapper;
import me.highgo.back.model.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PrivilegeService.java
 *
 * @Description :
 * @Author huangzhiwei
 * @DATE 2016/5/23
 */
@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    public List<Privilege> selectListByRid(Map<String,Integer> param){
        Integer rid = param.get("rid");
        List<Privilege> privileges = privilegeMapper.selectListByRid(rid);
        return privileges;
    }
}
