package com.store.member.impl;

import javax.validation.constraints.NotNull;

public class MemberDto {
    @NotNull(message = "O userId não pode ser nulo!")
    private Long userId;
    @NotNull(message = "O groupId não pode ser nulo!")
    private Long groupId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
