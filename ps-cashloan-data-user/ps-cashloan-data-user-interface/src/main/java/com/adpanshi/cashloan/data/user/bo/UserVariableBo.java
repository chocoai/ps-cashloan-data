package com.adpanshi.cashloan.data.user.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.variable.enums.VariableType;

/**
 * 用户变量数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserVariableBo implements java.io.Serializable, BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private String variableExtractVersion;          //变量抽取程序版本
    private VariableType variableType;                   //变量类型
    private Integer variableDataId;                 //变量数据主键
    private UserMetaDataBo userVariableDataFrom;  //变量数据来源
    private String createTime;                //变量创建时间

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

    public UserMetaDataBo getUserVariableDataFrom() {
        return userVariableDataFrom;
    }

    public void setUserVariableDataFrom(UserMetaDataBo userVariableDataFrom) {
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
        if (variableType != null && variableType instanceof String) {
            this.setVariableType(VariableType.getByValue((String) variableType));
        }

        Object userVariableDataFrom = BeanUtil.getPropValue(o, "userVariableDataFrom");
        if (userVariableDataFrom != null) {
            this.setUserVariableDataFrom(BeanUtil.convert(userVariableDataFrom,UserMetaDataBo.class));
        }
    }
}
