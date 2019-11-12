package com.keep.kit.smallpro.exception;

import com.keep.commons.core.exception.KeepException.KeepError;

/**
 * Date: 2018/9/14
 * Time: 下午2:15
 *
 * @author fanxiaodong
 */
public enum KitErrorCode implements KeepError {

    // 501xxx 系统错误
    PARAM_NOT_VALID(501001, "参数不合法"),
    DUPLICATE_ADD(501002, "重复插入");

    int httpStatus;
    int code;
    String msg;

    KitErrorCode(int code, String msg) {
        this.httpStatus = 400;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
