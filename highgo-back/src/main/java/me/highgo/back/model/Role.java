package me.highgo.back.model;

import java.util.List;

public class Role {
    private Integer id;

    private String roleName;

    private String roleDesc;

    private String roleDbprivilege;

    private String status;

    //该角色拥有的菜单列表
    private List<Menu> menuList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public String getRoleDbprivilege() {
        return roleDbprivilege;
    }

    public void setRoleDbprivilege(String roleDbprivilege) {
        this.roleDbprivilege = roleDbprivilege == null ? null : roleDbprivilege.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}