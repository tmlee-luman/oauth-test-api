package com.lumanlab.authtestserver.infrastructure;

import com.lumanlab.authtestserver.domain.Member;
import lombok.Getter;

@Getter
public class OauthUserInfo {

    private final String name;

    private final String email;

    public OauthUserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Member toEntity() {
        return Member.createMember(this.email, this.name);
    }
}
