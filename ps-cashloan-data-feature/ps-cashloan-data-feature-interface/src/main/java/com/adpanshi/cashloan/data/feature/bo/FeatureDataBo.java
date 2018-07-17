package com.adpanshi.cashloan.data.feature.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 特征数据bo
 *  Created by zsw on 2018/6/29 0029.
 */
public class FeatureDataBo implements Serializable ,BeanUtil.ConversionCustomizble{
    private static final long serialVersionUID = 1L;

    private Integer fid;//主键
    private String idcard;//身份证
    private String account;//账号
    private String name;//姓名
    private String equipmentFingerpints;//设备指纹
    private FeatureType featureType;//特征类型
    private String featureExtractVersion;//特征抽取版本
    private String createdTime;//创建时间
    private List<FeatureDataValueBo> valueList;//结果集
    private List<DataFromBo> dataFromList;//数据来源集

    public FeatureDataBo(){
        valueList = new ArrayList<FeatureDataValueBo>();
        dataFromList  = new ArrayList<DataFromBo>();
    }

    public List<FeatureDataValueBo> getValueList() {
        return valueList;
    }

    public void setValueList(List<FeatureDataValueBo> valueList) {
        this.valueList = valueList;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(FeatureType featureType) {
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

    public List<DataFromBo> getDataFromList() {
        return dataFromList;
    }

    public void setDataFromList(List<DataFromBo> dataFromList) {
        this.dataFromList = dataFromList;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public void convertOthers(Object o) {
        Object valueList = BeanUtil.getPropValue(o, "valueList");
        if (valueList != null && valueList instanceof List) {
            this.setValueList(BeanUtil.convertList((List)valueList,FeatureDataValueBo.class));
        }

        Object dataFromList = BeanUtil.getPropValue(o, "dataFromList");
        if (dataFromList != null && dataFromList instanceof List) {
            this.setDataFromList(BeanUtil.convertList((List)dataFromList,DataFromBo.class));
        }

        Object featureType = BeanUtil.getPropValue(o, "featureType");
        if (featureType != null && featureType instanceof String) {
            this.setFeatureType(FeatureType.getByValue((String)featureType));
        }
    }
}
