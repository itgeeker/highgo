package me.highgo.back.controller;

import me.highgo.back.model.Manager;
import me.highgo.back.model.Menu;
import me.highgo.back.model.MenuItem;
import me.highgo.back.model.Role;
import me.highgo.back.service.ManagerService;
import me.highgo.back.service.MenuService;
import me.highgo.back.service.PrivilegeService;
import me.highgo.back.service.RoleService;
import me.highgo.back.util.MD5;
import me.highgo.back.util.constants.ManageConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    public static Logger logger = LoggerFactory.getLogger(ManagerController.class);
    
    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@ModelAttribute("manager")Manager manager, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute(ManageConstants.MANAGE_SESSION_USER_INFO) != null) {
            return "redirect:/manage/user/home";
        }
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@ModelAttribute("manager")Manager manager, HttpServletRequest request,ModelMap model){
        //判断用户是否登录和校验登录信息
        HttpSession session = request.getSession();
        if (session.getAttribute(ManageConstants.MANAGE_SESSION_USER_INFO) != null) {
            return "redirect:/manage/user/home";
        }

        if (StringUtils.isBlank(manager.getUsername()) || StringUtils.isBlank(manager.getPassword())){
            model.addAttribute("errorMsg", "账户和密码不能为空!");
            return "login";
        }

        logger.info("用户登录:{}", manager.getUsername());
        manager = managerService.login(manager.getUsername(), MD5.md5(manager.getPassword()));
        String errorMsg;

        if (manager == null) {
            errorMsg = "登陆失败，账户或密码错误！";
            logger.error("登陆失败，账户或密码错误,{}", manager.getUsername());
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }else if(!"y".equals(manager.getStatus())){
            errorMsg = "帐号已被禁用，请联系管理员!";
            logger.error("帐号已被禁用，请联系管理员,{}", manager.getUsername());
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }

        session.setAttribute(ManageConstants.MANAGE_SESSION_USER_INFO, manager);

        //用户可访问的菜单写入session
        Collection<MenuItem> userMenus = loadMenus(manager);
        session.setAttribute("userMenus", userMenus);

        return "redirect:/manage/user/home";
    }

    private Collection<MenuItem> loadMenus(Manager manager) {
		/*
		 * 首先，加载顶级目录或页面菜单
		 */
        Map<String, Integer> param = new HashMap<String, Integer>();
        if (manager != null && manager.getRid() != null) {
            param.put("rid", manager.getRid());
        }

        List<Menu> menus = menuService.selectList(param);

        //创建菜单集合
        LinkedHashMap<String, MenuItem> root = new LinkedHashMap<String, MenuItem>();
        //循环添加菜单到菜单集合
        for (Menu menu : menus) {
            MenuItem item = new MenuItem(menu.getName(), null);
            item.setId(menu.getId().toString());
            item.setPid(menu.getPid().toString());
            item.setMenuType(menu);
            item.setUrl(StringUtils.trimToEmpty(menu.getUrl()));
            if(item.isRootMenu()) {
                root.put(item.getId(), item);
            }
        }
        for (Menu menu : menus) {
            MenuItem item = new MenuItem(menu.getName(), null);
            item.setId(menu.getId().toString());
            item.setPid(menu.getPid().toString());
            item.setMenuType(menu);
            item.setUrl(StringUtils.trimToEmpty(menu.getUrl()));
            if(!item.isRootMenu() && !item.isButton()) {
                MenuItem parentItem = root.get(item.getPid());
                if(parentItem != null) {
                    parentItem.addClild(item);
                } else {
                    logger.warn("菜单项{}({})没有对应的父级菜单", item.getName(), item.getId());
                }
            }
        }
        return root.values();
    }
}
