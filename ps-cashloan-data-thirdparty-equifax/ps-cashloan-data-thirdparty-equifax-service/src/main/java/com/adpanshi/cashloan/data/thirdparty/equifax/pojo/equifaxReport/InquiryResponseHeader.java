package com.adpanshi.cashloan.data.thirdparty.equifax.pojo.equifaxreport;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zsw on 2018/7/13 0013.
 */
@XStreamAlias("sch:InquiryResponseHeader")
public class InquiryResponseHeader implements Serializable {

    @XStreamAlias("sch:CustomerCode")
    private String customerCode;
    @XStreamAlias("sch:ClientID")
    private String clientID;
    @XStreamAlias("sch:CustRefField")
    private String custRefField;
    @XStreamAlias("sch:ReportOrderNO")
    private String reportOrderNO;
    @XStreamAlias("sch:ProductCode")
    private String productCode;
    @XStreamAlias("sch:SuccessCode")
    private String successCode;
    @XStreamAlias("sch:Date")
    private String date;
    @XStreamAlias("sch:Time")
    private String time;
    @XStreamAlias("sch:HitCode")
    private String hitCode;


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCustRefField() {
        return custRefField;
    }

    public void setCustRefField(String custRefField) {
        this.custRefField = custRefField;
    }

    public String getReportOrderNO() {
        return reportOrderNO;
    }

    public void setReportOrderNO(String reportOrderNO) {
        this.reportOrderNO = reportOrderNO;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHitCode() {
        return hitCode;
    }

    public void setHitCode(String hitCode) {
        this.hitCode = hitCode;
    }
}
