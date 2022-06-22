package com.lumanlab.authtestserver.service;

import com.lumanlab.authtestserver.domain.Member;
import com.lumanlab.authtestserver.domain.MemberRepository;
import com.lumanlab.authtestserver.exception.NotFoundMemberException;
import com.lumanlab.authtestserver.security.dto.AuthenticationMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(NotFoundMemberException::new);

        return new AuthenticationMember(member, List.of(new SimpleGrantedAuthority(member.getMemberRole().getRole())));
    }
}
