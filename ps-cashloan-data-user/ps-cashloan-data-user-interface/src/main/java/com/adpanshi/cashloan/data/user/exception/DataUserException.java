package com.adpanshi.cashloan.data.user.exception;

/**
 * Created by zsw on 2018/8/3 0003.
 */
public class DataUserException extends RuntimeException {
    public DataUserException(){}

    public DataUserException(String message){
        super(message);
    }

    public DataUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataUserException(Throwable cause) {
        super(cause);
    }
}
