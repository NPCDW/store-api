package com.github.npcdw.storeapi.exception;

import com.github.npcdw.storeapi.entity.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> exception(HttpServletRequest request, Exception ex) {
        log.error("系统异常: method: {}, path: {}, stackInfo:", request.getMethod(), request.getRequestURI(), ex);
        return ResponseResult.error("接口调用失败，未知错误");
    }

}
