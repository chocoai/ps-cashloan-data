package com.adpanshi.cashloan.data.user.pojo;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.user.bo.UserMetaDataBo;
import com.adpanshi.cashloan.data.variable.enums.VariableType;

/**
 * 用户变量数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserVariable implements java.io.Serializable, BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private String variableExtractVersion;          //变量抽取程序版本
    private String variableType;                   //变量类型
    private Integer variableDataId;                 //变量数据主键
    private UserMetaData userVariableDataFrom;      //变量数据来源
    private String createTime;                      //变量创建时间


    public String getVariableExtractVersion() {
        return variableExtractVersion;
    }

    public void setVariableExtractVersion(String variableExtractVersion) {
        this.variableExtractVersion = variableExtractVersion;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public Integer getVariableDataId() {
        return variableDataId;
    }

    public void setVariableDataId(Integer variableDataId) {
        this.variableDataId = variableDataId;
    }

    public UserMetaData getUserVariableDataFrom() {
        return userVariableDataFrom;
    }

    public void setUserVariableDataFrom(UserMetaData userVariableDataFrom) {
        this.userVariableDataFrom = userVariableDataFrom;
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
        if (variableType != null && variableType instanceof VariableType) {
            this.setVariableType(((VariableType) variableType).getValue());
        }

        Object userVariableDataFrom = BeanUtil.getPropValue(o, "userVariableDataFrom");
        if (userVariableDataFrom != null && userVariableDataFrom instanceof UserMetaDataBo) {
            this.setUserVariableDataFrom(BeanUtil.convert(userVariableDataFrom,UserMetaData.class));
        }
    }
}
