package com.king.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.domain
 * @ClassName: Role
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/25 20:27
 * @Version: 1.0
 */
@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    //配置多对多
    @ManyToMany(mappedBy = "roles")
    private Set<User> Users=new HashSet<User>();


    public Set<User> getUsers() {
        return Users;
    }

    public void setUsers(Set<User> users) {
        Users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", Users=" + Users +
                '}';
    }
}
