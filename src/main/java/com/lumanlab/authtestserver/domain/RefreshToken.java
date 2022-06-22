package com.lumanlab.authtestserver.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@RedisHash("refreshToken_admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    private String token;

    @TimeToLive(unit = TimeUnit.DAYS)
    private long expirationIn;

    private LocalDateTime expiredDateTime;

    public RefreshToken(String refreshToken, long expirationIn, Date expiredDate) {
        this.token = refreshToken;
        this.expirationIn = expirationIn;
        this.expiredDateTime = expiredDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
