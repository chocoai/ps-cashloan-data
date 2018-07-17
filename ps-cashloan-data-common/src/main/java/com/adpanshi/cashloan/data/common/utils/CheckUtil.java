package com.adpanshi.cashloan.data.common.utils;

import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.common.exception.ParamCheckErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class CheckUtil {

    private static Logger LOGGER = null;

    public static void checkStatus(boolean status, String message) throws BusinessException {
        if (status)
            throw new BusinessException(message);
    }

    public static void checkNotNull(Object o) throws BusinessException {
        if (o == null ||"".equals(o)) {
            throw new ParamCheckErrorException("数据不能为空!");
        }
    }

    public static void checkNotNull(Object o,String msg) throws BusinessException {
        if (o == null) {
            throw new ParamCheckErrorException(msg == null ? "数据不能为空!" : msg);
        }
    }

    public static void checkParam(boolean status, String message) throws BusinessException {
        if (status)
            throw new ParamCheckErrorException(message);
    }

    public static <T> void checkListSize(List<T> list, String message) throws BusinessException {
        CheckUtil.checkStatus(null == list || 0 == list.size(), "列表信息不允许为空");
        if (list.size() != 1) {
            throw new BusinessException(message);
        }
    }

    public static <T> Boolean checkListIsNullOrIsEmpty(List<T> list) {
        if (null == list || 0 == list.size())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public static void checkStatusAndPrintWarnLog(Class clazz, String message) {
        LOGGER = LoggerFactory.getLogger(clazz);
        LOGGER.warn(message);
    }

    public static void checkStatusAndPrintErrorLog(Class clazz, String message) {
        LOGGER = LoggerFactory.getLogger(clazz);
        LOGGER.error(message);
    }
}
