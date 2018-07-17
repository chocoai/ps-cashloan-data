package com.adpanshi.cashloan.data.variable.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.variable.enums.VariableType;

import java.io.Serializable;

/**
 * 变量抽取结果
 * Created by zsw on 2018/6/29 0029.
 */
public class VariableExtractResultBo implements Serializable, BeanUtil.ConversionCustomizble{
    private static final long serialVersionUID = 1L;

    private String variableExtractVersion;//变量抽取版本
    private VariableType variableType;      //变量类别
    private Integer variableDataId;         //变量主键
    private DataFromBo dataFromBo;          //变量数据来源
    private String createTime;              //创建时间

    public String getVariableExtractVersion() {
        return variableExtractVersion;
    }

    public void setVariableExtractVersion(String variableExtractVersion) {
        this.variableExtractVersion = variableExtractVersion;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    public Integer getVariableDataId() {
        return variableDataId;
    }

    public void setVariableDataId(Integer variableDataId) {
        this.variableDataId = variableDataId;
    }

    public DataFromBo getDataFromBo() {
        return dataFromBo;
    }

    public void setDataFromBo(DataFromBo dataFromBo) {
        this.dataFromBo = dataFromBo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public void convertOthers(Object o) {
        Object variableType = BeanUtil.getPropValue(o, "variableType");
        if (variableType != null && variableType instanceof String) {
            this.setVariableType(VariableType.getByValue((String)variableType));
        }
    }
}
