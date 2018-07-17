package com.adpanshi.cashloan.data.appdata.exception;

/**
 * Created by zsw on 2018/7/14 0014.
 */
public class AppDataException extends RuntimeException {
    public AppDataException(){}

    public AppDataException(String message){
        super(message);
    }

    public AppDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppDataException(Throwable cause) {
        super(cause);
    }
}
