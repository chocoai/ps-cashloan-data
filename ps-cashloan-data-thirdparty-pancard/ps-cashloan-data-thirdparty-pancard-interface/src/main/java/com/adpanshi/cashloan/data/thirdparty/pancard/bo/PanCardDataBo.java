package com.adpanshi.cashloan.data.thirdparty.pancard.bo;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zsw on 2018/8/2 0002.
 */
public class PanCardDataBo implements java.io.Serializable, BeanUtil.ConversionCustomizble {
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
     * 账号
     */
    private String account;
    /**
     * 原始数据
     */
    private String originalData;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 设备指纹
     */
    private  String deviceFingerprint;

    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
    @Override
    public void convertOthers(Object o) {
        Object createTime = BeanUtil.getPropValue(o, "createTime");
        if (createTime != null && createTime instanceof String) {
            this.setCreateTime(DateUtil.string2Date((String)createTime, DateUtil.ymdhmsSSSFormat));
        }
    }
}
