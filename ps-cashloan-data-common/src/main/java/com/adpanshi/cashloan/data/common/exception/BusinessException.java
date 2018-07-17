package com.adpanshi.cashloan.data.common.exception;

import com.adpanshi.cashloan.common.exception.PSException;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class BusinessException extends PSException {

    /**
     * 异常
     */
    public BusinessException() {
        super();
    }

    /**
     * @param message
     *            异常消息
     * @param cause
     *            异常原因
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     *            异常消息
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * @param cause
     *            异常原因
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }
}
