package com.adpanshi.cashloan.data.feature.rule;

import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class DataTypeExchangeRule {
    /**
     *
     * @param variableData
     * @return 类型转换后的数据
     */

    public Integer getStrToInt(VariableDataValueBo variableData){

        String value =  (String)variableData.getValue();

        Integer result= 0;

        if(value!=null) {
            if("男".equals(value)){
                result=1;
            }
            else if("女".equals(value)){
                result=2;
            }else {
                result = Integer.parseInt(value);
            }
        }
        return result;

    }


    public Double getStrToDou(VariableDataValueBo variableData){

        String value =  (String)variableData.getValue();

        double result= 0;

        if(value!=null){
            result=Double.parseDouble(value);
        }

        return result;
    }
}
