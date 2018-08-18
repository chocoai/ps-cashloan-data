package com.adpanshi.cashloan.data.cunsumerloanhistory.exception;

/**
 * Created by zsw on 2018/8/4 0004.
 */
public class CunsumerLoanHistoryDataException extends RuntimeException {
    public CunsumerLoanHistoryDataException(){}

    public CunsumerLoanHistoryDataException(String message){
        super(message);
    }

    public CunsumerLoanHistoryDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CunsumerLoanHistoryDataException(Throwable cause) {
        super(cause);
    }
}
