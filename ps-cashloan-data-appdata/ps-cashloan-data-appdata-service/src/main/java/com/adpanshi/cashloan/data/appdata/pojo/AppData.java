package com.adpanshi.cashloan.data.appdata.pojo;

import java.io.Serializable;

/**
 * Created by zsw on 2018/8/2 0002.
 */
public class AppData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer fid;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证
     */
    private String aadhaarNo;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 原始数据
     */
    private String originalData;
    /**
     * 设备指纹
     */
    private  String deviceFingerprint;

    /**
     * 创建时间
     */
    private String createTime;

    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }
}
