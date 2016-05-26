package me.highgo.back.controller;

import me.highgo.back.model.*;
import me.highgo.back.service.*;
import me.highgo.back.util.MD5;
import me.highgo.back.util.constants.ManageConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class UserController {

    public static Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private SystemSettingService systemSettingService;

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
        //循环添加根菜单到菜单集合
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
        //找到子菜单的父亲并连接
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

    @RequestMapping("/home")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Manager manager= (Manager)session.getAttribute(ManageConstants.MANAGE_SESSION_USER_INFO);
        if (manager!=null){
            model.addAttribute("currentUser",manager);
            SystemSetting systemSetting = systemSettingService.selectByPrimaryKey(1);
            model.addAttribute("systemSetting",systemSetting);
            return "home";
        }
        return "login";
    }

    @RequestMapping("/initManageIndex")
    public String initManageIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Manager manager= (Manager)session.getAttribute(ManageConstants.MANAGE_SESSION_USER_INFO);
        model.addAttribute("currentUser",manager);
        SystemSetting systemSetting = systemSettingService.selectByPrimaryKey(1);
        model.addAttribute("systemSetting",systemSetting);
        return "home";
    }

    @RequestMapping("/show")
    public String show(@ModelAttribute("manager") Manager manager,String account, Model model){
        if(StringUtils.isBlank(account)){
            throw new NullPointerException("非法请求！");
        }
        manager.setUsername(account);
        manager = managerService.selectManagerInfo(manager);
        model.addAttribute("manager", manager);
        return "/manage/system/user/show";
    }

    /**
     * 转到修改密码页面
     * @ModelAttribute注解：自动绑定多个请求参数到一个命令对象上，并且自动暴露模型数据用于视图页面展示使用
     * @return
     */
    @RequestMapping("toChangePwd")
    public String toChangePwd(HttpSession session, @ModelAttribute("e") Manager e,Model model){
        Manager u = (Manager) session.getAttribute(ManageConstants.MANAGE_SESSION_USER_INFO);
        model.addAttribute("currentUser",u);
        e.setId(u.getId());
        return "/manage/system/user/toChangePwd";
    }

    /**
     * 注销登录
     * @return
     * @throws Exception
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,@ModelAttribute("e") Manager manager) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) {
            session.setAttribute(ManageConstants.MANAGE_SESSION_USER_INFO, null);
        }
        manager.clear();
        return "login";
    }

}
