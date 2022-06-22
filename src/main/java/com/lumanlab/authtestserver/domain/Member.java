package com.lumanlab.authtestserver.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "member")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private Member(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static Member createMember(String email, String name) {
        return new Member(email, name);
    }
}
