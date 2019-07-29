package com.store.permission.impl;

import com.store.group.impl.Group;
import com.store.role.impl.Role;

public class Permission {

    private Long id;
    private Group group;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
