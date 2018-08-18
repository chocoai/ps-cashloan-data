package com.adpanshi.cashloan.data.thirdparty.equifax.pojo;

import java.io.Serializable;

/**
 * @program: hujin3
 * @description: 信用报告日志
 * @author: Mr.Wange
 * @create: 2018-08-03 23:04
 **/
public class EquifaxReportDataLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer fid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 日志状态
     */
    private String status;
    /**
     * 上送参数
     */
    private String parameterData;
    /**
     * 原始数据
     */
    private String originalData;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * uuid
     */
    private String uuid;

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

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParameterData() {
        return parameterData;
    }

    public void setParameterData(String parameterData) {
        this.parameterData = parameterData;
    }
}
