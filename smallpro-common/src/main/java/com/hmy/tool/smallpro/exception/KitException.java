package com.keep.kit.smallpro.exception;

import com.keep.commons.core.exception.KeepException;

/**
 * Date: 2018/9/14
 * Time: 下午2:16
 *
 * @author fanxiaodong
 */
public class KitException extends KeepException {

    public KitException(KeepError error) {
        super(error);
    }

    public KitException(KeepError error, Throwable cause) {
        super(error, cause);
    }

    public KitException(int errorCode, String message) {
        super(400, errorCode, message);
    }
}
