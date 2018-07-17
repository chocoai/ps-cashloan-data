package com.adpanshi.cashloan.data.user.bo;


import com.adpanshi.cashloan.common.enums.OrganizationEnum;
import com.adpanshi.cashloan.common.utils.BeanUtil;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 用户数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserDataBo extends UserBaseDataBo implements java.io.Serializable, BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private Integer fid;//主键
    private List<UserMetaDataBo> userMetaDataList;//原始数据
    private List<UserVariableBo> userVariableList;//变量
    private List<UserFeatureBo> userFeatureList;//特征
    private String createTime;
    private String lastModifyTime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public List<UserMetaDataBo> getUserMetaDataList() {
        return userMetaDataList;
    }

    public void setUserMetaDataList(List<UserMetaDataBo> userMetaDataList) {
        this.userMetaDataList = userMetaDataList;
    }

    public List<UserVariableBo> getUserVariableList() {
        return userVariableList;
    }

    public void setUserVariableList(List<UserVariableBo> userVariableList) {
        this.userVariableList = userVariableList;
    }

    public List<UserFeatureBo> getUserFeatureList() {
        return userFeatureList;
    }

    public void setUserFeatureList(List<UserFeatureBo> userFeatureList) {
        this.userFeatureList = userFeatureList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public void convertOthers(Object o) {
        Object countryType = BeanUtil.getPropValue(o, "countryType");
        if (countryType != null && countryType instanceof Integer) {
            this.setCountryType(OrganizationEnum.CountryType.valueOf((Integer)countryType));
        }

        Object productType = BeanUtil.getPropValue(o, "productType");
        if (productType != null && productType instanceof Integer) {
            this.setProductType(OrganizationEnum.ProductType.valueOf((Integer)productType));
        }

        Object userMetaDataList = BeanUtil.getPropValue(o, "userMetaDataList");
        if (userMetaDataList != null && userMetaDataList instanceof List) {
            this.setUserMetaDataList(BeanUtil.convertList(((List) userMetaDataList), UserMetaDataBo.class));
        }

        Object userVariableList = BeanUtil.getPropValue(o, "userVariableList");
        if (userVariableList != null && userVariableList instanceof List) {
            this.setUserVariableList(BeanUtil.convertList(((List) userVariableList), UserVariableBo.class));
        }

        Object userFeatureList = BeanUtil.getPropValue(o, "userFeatureList");
        if (userFeatureList != null && userFeatureList instanceof List) {
            this.setUserFeatureList(BeanUtil.convertList(((List) userFeatureList), UserFeatureBo.class));
        }

    }
}
