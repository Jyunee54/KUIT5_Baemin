package com.kuit.baemin.controller.connection;

import com.kuit.baemin.utils.BaseResponse;
import com.kuit.baemin.utils.BaseResponseStatus;
import com.kuit.baemin.validator.SignupValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class UserControllerAdvice {
    private final SignupValidator signupValidator;

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new BaseResponse<>(BaseResponseStatus.NON_MATCH_PASSWORD);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(signupValidator);
    }
}
