package com.kuit.baemin.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import static com.kuit.baemin.utils.BaseResponseStatus.SUCCESS;

@Getter
public class BaseResponse<T> {

    private final Boolean isSuccess;

    private final int responseCode;
    private final String responseMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final T result;

    public BaseResponse(T result){
        this.isSuccess = SUCCESS.isSuccess();
        this.responseMessage = SUCCESS.getResponseMessage();
        this.responseCode = SUCCESS.getResponseCode();
        this.result = result;
    }

    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.responseMessage = status.getResponseMessage();
        this.responseCode = status.getResponseCode();
        this.result = null;
    }
}