package com.lumanlab.authtestserver.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
public class AccessToken {

    private final String token;

    private final LocalDateTime expiredDateTime;

    public AccessToken(String token, Date expiredDate) {
        this.token = token;
        this.expiredDateTime = expiredDateToLocalDateTime(expiredDate);
    }

    private LocalDateTime expiredDateToLocalDateTime(Date expiredDate) {
        return expiredDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
