package com.latinhouse.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS("이미 존재하는 이메일입니다", HttpStatus.CONFLICT),
    INVALID_REQUEST("잘못된 요청입니다", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
