package com.keep.kit.smallpro.webapp.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.keep.commons.core.exception.KeepException;
import com.keep.commons.web.model.response.ResponseEntity;
import com.keep.kit.smallpro.exception.KitErrorCode;
import com.keep.kit.smallpro.log.SentryLogger;
import com.keep.kit.smallpro.utils.FormValidationUtil;

/**
 * Date: 2018/9/14
 * Time: 下午3:40
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
@ControllerAdvice
public class ApiExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {KeepException.class})
    @ResponseBody
    public ResponseEntity handleException(KeepException e, HttpServletRequest request,
            HttpServletResponse response) {

        // for debug use only
        logger.debug(e.getMessage(), e);

        // set the http status code
        response.setStatus(e.getHttpStatusCode());

        // return the error code in json
        return new ResponseEntity(e.getErrorCode(), e.getMessage());
    }

    /**
     * This handler method will handle all exceptions other than KeepException
     */
    @ExceptionHandler(value = {Throwable.class})
    @ResponseBody
    public String handleOtherException(Throwable e, HttpServletRequest request,
            HttpServletResponse response) {

        if (e instanceof NoHandlerFoundException) {
            // 404 error
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return StringUtils.EMPTY;
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            // 405 error
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            int errorCode = KeepException.KeepBasicErrorCode.METHOD_NOT_ALLOWED.getCode();
            ResponseEntity entity = new ResponseEntity(errorCode, e.getMessage());
            return entity.toResponseJson();
        } else if (e instanceof ServletRequestBindingException
                || e instanceof TypeMismatchException
                || e instanceof HttpMediaTypeException) {
            // 400 error
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            int errorCode = KeepException.KeepBasicErrorCode.MISSING_PARAMETER.getCode();
            ResponseEntity entity = new ResponseEntity(errorCode, e.getMessage());
            return entity.toResponseJson();
        } else if (e instanceof MethodArgumentNotValidException) {
            // 参数校验不通过
            KitErrorCode errorCode = KitErrorCode.PARAM_NOT_VALID;
            StringBuilder message = new StringBuilder(errorCode.getMsg());
            BindingResult br = ((MethodArgumentNotValidException) e).getBindingResult();
            if (br != null) {
                message = message.append("-> ").append(FormValidationUtil.getErrorMessages(br));
            }
            ResponseEntity responseEntity = new ResponseEntity(errorCode.getCode(), message.toString());
            return responseEntity.toResponseJson();
        } else {
            // 500 error
            SentryLogger.SENTRY_LOGGER.error(e.getMessage(), e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return StringUtils.EMPTY;
        }
    }
}
