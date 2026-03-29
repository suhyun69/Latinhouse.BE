package com.latinhouse.backend.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode code) {
        super(code.getMessage());
        this.errorCode = code;
    }

    public CustomException(ErrorCode code, String message) {
        super(message);
        this.errorCode = code;
    }
}
