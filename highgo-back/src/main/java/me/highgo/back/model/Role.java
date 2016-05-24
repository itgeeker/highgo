package me.highgo.back.model;

public class Role {
    private Integer id;

    private String roleName;

    private String roleDesc;

    private String roleDbprivilege;

    private String status;

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

}