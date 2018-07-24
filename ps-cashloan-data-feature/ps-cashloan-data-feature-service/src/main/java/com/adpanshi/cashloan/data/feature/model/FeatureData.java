package com.adpanshi.cashloan.data.feature.model;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.feature.pojo.FeatureDataValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class FeatureData implements Serializable,BeanUtil.ConversionCustomizble{
    private static final long serialVersionUID = 1L;

    private Integer fid;//主键
    private String aadhaarNo;//aadhaarNo编号
    private String email;//邮箱
    private String mobile;//手机号
    private String name;//姓名
    private String equipmentFingerpints;//设备指纹
    private String featureType;//特征类型
    private String featureExtractVersion;//特征抽取器版本
    private String createdTime;//创建时间
    private List<FeatureDataValue> valueList;//特征值list
    private List<DataFrom> dataFromList;//数据来源list

    public FeatureData(){
        valueList = new ArrayList<FeatureDataValue>();
        dataFromList  = new ArrayList<DataFrom>();
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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public String getFeatureExtractVersion() {
        return featureExtractVersion;
    }

    public void setFeatureExtractVersion(String featureExtractVersion) {
        this.featureExtractVersion = featureExtractVersion;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<FeatureDataValue> getValueList() {
        return valueList;
    }

    public void setValueList(List<FeatureDataValue> valueList) {
        this.valueList = valueList;
    }

    public void addValueList(String content,String key,Object value,Integer valueType){
        FeatureDataValue valueItem = new FeatureDataValue();
        valueItem.setKey(key);
        valueItem.setValue(value);
        valueItem.setValueType(valueType);
        valueItem.setContent(content);
        this.valueList.add(valueItem);
    }
    public void addValueList(FeatureDataValue featureDataValue){
        this.valueList.add(featureDataValue);
    }

    public List<DataFrom> getDataFromList() {
        return dataFromList;
    }

    public void setDataFromList(List<DataFrom> dataFromList) {
        this.dataFromList = dataFromList;
    }

    public void addDataFrom(DataFrom dataFrom) {
        this.dataFromList.add(dataFrom);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void convertOthers(Object o) {
        Object featureType = BeanUtil.getPropValue(o, "featureType");
        if (featureType != null && featureType instanceof FeatureType) {
            this.setFeatureType(((FeatureType)featureType).getValue());
        }
    }
}