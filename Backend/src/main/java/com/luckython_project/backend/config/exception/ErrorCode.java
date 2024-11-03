package com.luckython_project.backend.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"U001","User not found"),
    USER_FAILED_CREATION(HttpStatus.BAD_REQUEST,"U002","User creation failed"),

    // RANKING
    RANK_NOT_FOUND(HttpStatus.NOT_FOUND,"R001","Rank not found"),

    // CHALLENGE
    CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND,"C001","Challenge not found");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
