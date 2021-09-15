package com.worldbright.springblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"), MEMBER("ROLE_MEMBER");
    private String role;
}
