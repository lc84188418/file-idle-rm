package com.cvmaster.fileidlerm.handler;

import com.cvmaster.fileidlerm.entity.CommonR;
import com.cvmaster.fileidlerm.enums.RStatus;
import com.cvmaster.fileidlerm.exception.Code;
import com.cvmaster.fileidlerm.exception.SystemInternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.StringJoiner;

/**
 * @Desc 全局异常处理
 * @author liuchun
 * @version 1.0.0
 * @createTime 2021年8月21日 09:55:56
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * 一、自定义异常
     */
    /**
     *  通用系统异常
     */
    @ExceptionHandler({SystemInternalException.class,Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonR handleSystemInternalException(Exception e) {
        log.error("系统内部异常，异常信息：", e);
        String message = e instanceof SystemInternalException? Code.C500.getDesc():e.getMessage();
        return new CommonR().msg(message).code(Code.C500.getCode()).status(RStatus.ERROR.status());
    }

    /**
     *  body参数验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonR methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("body参数验证异常，异常信息：", e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringJoiner sj = new StringJoiner(",");
        for (ObjectError error:allErrors){
            sj.add(error.getDefaultMessage());
        }
        return new CommonR().msg(sj.toString()).code(Code.C444.getCode()).status(RStatus.ERROR.status());
    }


    /**
     *  二、系统异常
     */
    /**
     *  HTTP请求方式不被支持
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public CommonR handleHttpRequestMethodNotSupportedException(Exception e) {
        log.error("HTTP请求方式不被支持：", e);
        return new CommonR().msg(Code.C405.getDesc()).code(Code.C405.getCode()).status(RStatus.ERROR.status());
    }

    /**
     *  HTTP请求内容未找到
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonR handleNoHandlerFoundException(Exception e) {
        log.error("HTTP请求内容未找到：", e);
        return new CommonR().msg(Code.C404.getDesc()).code(Code.C404.getCode()).status(RStatus.ERROR.status());
    }

    /**
     *  不支持的媒体类型
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CommonR handleHttpMediaTypeNotSupportedException(Exception e) {
        log.warn(e.getMessage());
        return new CommonR().msg(Code.C415.getDesc()).code(Code.C415.getCode()).status(RStatus.ERROR.status()).data(e.getMessage());
    }

    /**
     *  参数列表不对
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonR handleMissingServletRequestParameterException(Exception e) {
        log.error(e.getMessage());
        return new CommonR().msg(Code.C407.getDesc()).code(Code.C407.getCode()).status(RStatus.ERROR.status()).data(e.getMessage());
    }

    /**
     *  数据库插入时，唯一性索引冲突
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonR handleDuplicateKeyException(Exception e) {
        log.error(e.getMessage());
        return new CommonR().msg(e.getMessage()).code(Code.C408.getCode()).status(RStatus.ERROR.status()).data(e.getMessage());
    }

}
