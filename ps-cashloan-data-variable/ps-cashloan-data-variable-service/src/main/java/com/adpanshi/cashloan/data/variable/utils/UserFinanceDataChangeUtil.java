package com.adpanshi.cashloan.data.variable.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class UserFinanceDataChangeUtil {

    private static String [] key_list = {
            "-1",
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "1a",
            "1b",
            "1c",
            "1d",
            "1e",
            "1f",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
    };

    private static String [] key_list_change={
            "-1",
            "[0,1000)",
            "[1000,2000)",
            "[2000,3000)",
            "[3000,4000)",
            "[4000,5000)",
            "[5000,6000)",
            "[6000,7000)",
            "[7000,8000)",
            "[8000,9000)",
            "[9000,10000)",
            "[10000,20000)",
            "[20000,30000)",
            "[30000,40000)",
            "[40000,50000)",
            "[50000,60000)",
            "[60000,70000)",
            "[70000,80000)",
            "[80000,90000)",
            "[90000,100000)",
            "[100000,200000)",
            "[200000,300000)",
            "[300000,400000)",
            "[400000,500000)",
            "[500000,600000)",
            "[600000,700000)",
            "[700000,800000)",
            "[800000,900000)",
            "[900000,1000000)",
            "[1000000,2000000)",
            "[2000000,3000000)",
            "[3000000,4000000)",
            "[4000000,5000000)",
            "[5000000,6000000)",
            "[6000000,7000000)",
            "[7000000,8000000)",
            "[8000000,9000000)",
            "[9000000,10000000)",
            "[10000000,+)",
    };

    public static Map<String,String> getCode() throws Exception{

        Map<String,String> map = new LinkedHashMap<String, String>();

        if(key_list.length!=key_list_change.length){
            throw new Exception("key_list.length!=key_list_change.length");
        }else {
            for(int i=0;i<key_list.length;i++){
                map.put(key_list[i],key_list_change[i]);
            }
        }
        return map;
    }
}
