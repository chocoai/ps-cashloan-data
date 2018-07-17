package com.adpanshi.cashloan.data.common.exception;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class ParamCheckErrorException extends BusinessException {

    /**
     * @param message 异常消息
     */
    public ParamCheckErrorException(String message) {
        super(message);
    }
}