package com.adpanshi.cashloan.data.feature.utils;


import com.adpanshi.cashloan.data.feature.bo.DataFromBo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by icarus on 2017/8/31.
 */
public class CommonUtil {

    public static List<Integer> dataFromBoList2DataIdList(List<DataFromBo> variableDataIdList){
        List<Integer> list = new ArrayList<>();
        for (DataFromBo dataFromBo : variableDataIdList){
            list.add(dataFromBo.getVariableDataId());
        }
        return list;
    }

    public static String getMicroSecondFormatString(Date c) {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (c != null) {
            return df.format(c);
        }else {
            return df.format(new Date());
        }
    }

    public static String addMonth(String date, int add){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cl = Calendar.getInstance();
        try {
            cl.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cl.add(Calendar.MONTH, add);
        return sdf.format(cl.getTime());
    }
}
