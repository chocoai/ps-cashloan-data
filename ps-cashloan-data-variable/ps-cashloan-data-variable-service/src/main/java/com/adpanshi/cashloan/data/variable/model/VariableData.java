package com.adpanshi.cashloan.data.variable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class VariableData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer fid;//主键
    private String aadhaarNo;//aadhaar编号
    private String account;//账号
    private String name;//姓名
    private String equipmentFingerpints;//设备指纹
    private String variableType;//变量类型
    private String variableExtractVersion;//变量抽取版本
    private List<VariableDataValue> valueList;//变量结果集
    private DataFrom dataFrom;//数据来源
    private String createTime;//创建时间

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public VariableData(){
        valueList = new ArrayList<VariableDataValue>();
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getEquipmentFingerpints() {
        return equipmentFingerpints;
    }

    public void setEquipmentFingerpints(String equipmentFingerpints) {
        this.equipmentFingerpints = equipmentFingerpints;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
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

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public String getVariableExtractVersion() {
        return variableExtractVersion;
    }

    public void setVariableExtractVersion(String variableExtractVersion) {
        this.variableExtractVersion = variableExtractVersion;
    }

    public List<VariableDataValue> getValueList() {
        return valueList;
    }

    public void setValueList(List<VariableDataValue> valueList) {
        this.valueList = valueList;
    }

    public void addValueList(String key,String content,Object value,Integer valueType){
        VariableDataValue valueItem = new VariableDataValue();
        valueItem.setKey(key);
        valueItem.setValue(value);
        valueItem.setValueType(valueType);
        valueItem.setContent(content);
        this.valueList.add(valueItem);
    }
    public void addValueList(VariableDataValue variableDataValue){
        this.valueList.add(variableDataValue);
    }

    public DataFrom getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(DataFrom dataFrom) {
        this.dataFrom = dataFrom;
    }



}
