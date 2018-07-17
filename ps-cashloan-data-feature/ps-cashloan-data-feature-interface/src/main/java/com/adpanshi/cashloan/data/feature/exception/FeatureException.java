package com.adpanshi.cashloan.data.feature.exception;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class FeatureException extends RuntimeException{

    public FeatureException(){}

    public FeatureException(String message){
        super(message);
    }

    public FeatureException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeatureException(Throwable cause) {
        super(cause);
    }
}
