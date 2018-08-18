package com.adpanshi.cashloan.data.thirdparty.pancard.exception;

/**
 * Created by zsw on 2018/8/3 0003.
 */
public class PanCardException  extends RuntimeException {
    public PanCardException(){}

    public PanCardException(String message){
        super(message);
    }

    public PanCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public PanCardException(Throwable cause) {
        super(cause);
    }
}
