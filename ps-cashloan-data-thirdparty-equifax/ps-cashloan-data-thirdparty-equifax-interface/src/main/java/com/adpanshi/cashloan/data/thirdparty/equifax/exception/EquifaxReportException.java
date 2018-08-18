package com.adpanshi.cashloan.data.thirdparty.equifax.exception;

/**
 * Created by zsw on 2018/8/3 0003.
 */
public class EquifaxReportException extends RuntimeException {
    public EquifaxReportException(){}

    public EquifaxReportException(String message){
        super(message);
    }

    public EquifaxReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquifaxReportException(Throwable cause) {
        super(cause);
    }
}
