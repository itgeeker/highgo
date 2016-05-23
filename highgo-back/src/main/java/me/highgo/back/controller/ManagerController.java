package me.highgo.back.controller;

import me.highgo.back.model.Manager;
import me.highgo.back.service.ManagerService;
import me.highgo.back.util.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * UserCenterController.java
 *
 * @Description : 后台用户Controller
 * @Author huangzhiwei
 * @DATE 2016/5/23
 */
@Controller
@RequestMapping("/manage/user")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@ModelAttribute("manager")Manager manager, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute(ManageConstants.MANAGE_SESSION_USER_INFO) != null) {
            return "redirect:/manage/user/home";
        }
        return "login";
    }

}
