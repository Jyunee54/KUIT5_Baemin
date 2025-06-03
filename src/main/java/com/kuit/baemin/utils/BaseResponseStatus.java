package com.kuit.baemin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    DUPLICATED_EMAIL(false, 2001, "중복된 이메일 입니다."),
    NON_MATCH_PASSWORD(false, 2002, "비밀번호가 일치하지 않습니다.");

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;

}