package com.lumanlab.authtestserver.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    A110("요청한 값 중 비어있는 값이거나, null일 수 없습니다."),
    A120("잘못된 값 요청입니다."),
    A190("잘못된 기타 양식입니다."),


    A210("로그인후 이용하실 있습니다."),
    A211("토큰이 만료되었거나, 잘못된 토큰입니다."),
    A220("접근권한입 없습니다. 상위관리자에게 문의하여 주십시오."),

    A310("찾는 데이터가 없습니다"),
    A311("증복된 데이터 입니다."),

    /**
     * 외부 서버 연동, 내부서버 예외
     */
    S110("Connection time out 외부서버 연동 실패"),
    S120("Connection refuse 외부서버 연동 거절"),

    S210("NPE 예외"),;


    private final String cause;

    ErrorCode(String cause) {
        this.cause = cause;
    }
}
