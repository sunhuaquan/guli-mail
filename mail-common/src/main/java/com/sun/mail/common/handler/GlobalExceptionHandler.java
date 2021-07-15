package com.sun.mail.common.handler;

import com.sun.mail.common.dto.R;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice(basePackages = "com.sun.mail.member.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public R exception(BindException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        return R.error(defaultMessage);
    }
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R exception(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        R.error(defaultMessage);
        return R.error(defaultMessage);

    }
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R exception(Exception exception) {
        return R.error("程序错误");

    }
}
