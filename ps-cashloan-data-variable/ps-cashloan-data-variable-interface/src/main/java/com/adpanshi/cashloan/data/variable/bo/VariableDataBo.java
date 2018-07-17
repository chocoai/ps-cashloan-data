package com.adpanshi.cashloan.data.variable.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.variable.enums.VariableType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class VariableDataBo implements Serializable, BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private Integer fid;//主键
    private String idcard;//身份证
    private String account;//账号
    private String name;//姓名
    private String equipmentFingerpints;//设备指纹
    private VariableType variableType;//变量类型
    private String variableExtractVersion;//变量抽取器版本号
    private List<VariableDataValueBo> valueList;//变量结果集
    private DataFromBo dataFrom;//数据来源
    private String createTime;//创建时间

    public VariableDataBo() {
        valueList = new ArrayList<VariableDataValueBo>();
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer id) {
        this.fid = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentFingerpints() {
        return equipmentFingerpints;
    }

    public void setEquipmentFingerpints(String equipmentFingerpints) {
        this.equipmentFingerpints = equipmentFingerpints;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getVariableExtractVersion() {
        return variableExtractVersion;
    }

    public void setVariableExtractVersion(String variableExtractVersion) {
        this.variableExtractVersion = variableExtractVersion;
    }

    public List<VariableDataValueBo> getValueList() {
        return valueList;
    }

    public void setValueList(List<VariableDataValueBo> valueList) {
        this.valueList = valueList;
    }

    public void addValueList(String key, String content, Object value, DataTypeEnum valueType) {
        VariableDataValueBo valueItem = new VariableDataValueBo();
        valueItem.setKey(key);
        valueItem.setValue(value);
        valueItem.setValueType(valueType);
        valueItem.setContent(content);
        this.valueList.add(valueItem);
    }

    public void addValueList(VariableDataValueBo variableDataValue) {
        this.valueList.add(variableDataValue);
    }

    public DataFromBo getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(DataFromBo dataFrom) {
        this.dataFrom = dataFrom;
    }


    @Override
    public void convertOthers(Object o) {
        Object valueList = BeanUtil.getPropValue(o, "valueList");
        if (valueList != null && valueList instanceof List) {
            this.setValueList(BeanUtil.convertList((List) valueList, VariableDataValueBo.class));
        }
        Object dataFrom = BeanUtil.getPropValue(o, "dataFrom");
        if (valueList != null) {
            this.setDataFrom(BeanUtil.convert(dataFrom, DataFromBo.class));
        }
        Object variableType = BeanUtil.getPropValue(o, "variableType");
        if (variableType != null && variableType instanceof String) {
            this.setVariableType(VariableType.getByValue((String) variableType));
        }
    }

}

