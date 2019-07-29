package com.store.permission.impl;

import javax.validation.constraints.NotNull;

public class PermissionDto {

    @NotNull(message = "O campo roleId não pode estar nulo.")
    private Long roleId;
    @NotNull(message = "O campo groupId não pode estar nulo.")
    private Long groupId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
