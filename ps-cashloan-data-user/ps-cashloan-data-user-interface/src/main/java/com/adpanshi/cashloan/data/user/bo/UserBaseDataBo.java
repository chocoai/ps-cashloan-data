package com.adpanshi.cashloan.data.user.bo;

import com.adpanshi.cashloan.common.enums.OrganizationEnum;

/**
 * Created by zsw on 2018/7/15 0015.
 */
public class UserBaseDataBo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private OrganizationEnum.CountryType countryType;//国家
    private OrganizationEnum.ProductType productType;//产品类型
    private String aadhaarNo;//身份证
    private String name;//姓名
    private String mobile;//手机号
    private String email;//邮箱
    private String deviceFingerprint;//设备指纹

    public OrganizationEnum.CountryType getCountryType() {
        return countryType;
    }

    public void setCountryType(OrganizationEnum.CountryType countryType) {
        this.countryType = countryType;
    }

    public OrganizationEnum.ProductType getProductType() {
        return productType;
    }

    public void setProductType(OrganizationEnum.ProductType productType) {
        this.productType = productType;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
    }
}