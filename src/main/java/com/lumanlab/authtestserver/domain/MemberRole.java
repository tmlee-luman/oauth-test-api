package com.lumanlab.authtestserver.domain;

import lombok.Getter;

@Getter
public enum MemberRole {

    GUEST("ROLE_GUEST");

    private final String role;

    MemberRole(String role) {
        this.role = role;
    }
}
