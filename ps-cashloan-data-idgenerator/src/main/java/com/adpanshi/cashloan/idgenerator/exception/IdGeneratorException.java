package com.adpanshi.cashloan.idgenerator.exception;

import com.adpanshi.cashloan.data.common.exception.BusinessException;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class IdGeneratorException extends BusinessException {

    public IdGeneratorException() {
        super();
    }

    public IdGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdGeneratorException(String message) {
        super(message);
    }

    public IdGeneratorException(Throwable cause) {
        super(cause);
    }
}