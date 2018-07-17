package com.adpanshi.cashloan.data.variable.exception;


import com.adpanshi.cashloan.data.common.exception.BusinessException;

/**
 * 变量异常
 * Created by zsw on 2018/6/29 0029.
 */
public class VariableException extends BusinessException {

    public VariableException(){}

    public VariableException(String message){
        super(message);
    }

    public VariableException(String message, Throwable cause) {
        super(message, cause);
    }

    public VariableException(Throwable cause) {
        super(cause);
    }
}
