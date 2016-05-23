package me.highgo.back.service;

import me.highgo.back.mapper.UserMapper;
import me.highgo.back.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService.java
 *
 * @Description : 用户中心Service层
 * @Author huangzhiwei
 * @DATE 2016/5/23
 */
@Service
public class ManagerService {

    @Autowired
    private UserMapper userMapper;

    public Manager login(String username, String password){
        Manager manager = userMapper.selectByUsernameAndPwd(username, password);
        return manager;
    }
}
