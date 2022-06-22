package com.lumanlab.authtestserver.security.dto;

import com.lumanlab.authtestserver.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthenticationMember extends User {

    private final Long id;

    public AuthenticationMember(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), "", authorities);
        this.id = member.getId();
    }
}
