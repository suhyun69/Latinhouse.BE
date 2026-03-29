package com.latinhouse.backend.global.exception;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}

/*
    readUserPort.findUserByEmail(email)
        .orElseThrow(UserNotFoundException::new);
 */
