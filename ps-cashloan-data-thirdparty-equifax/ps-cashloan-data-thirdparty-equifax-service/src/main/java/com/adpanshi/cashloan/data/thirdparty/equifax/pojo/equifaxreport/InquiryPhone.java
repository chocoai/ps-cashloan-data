package com.adpanshi.cashloan.data.thirdparty.equifax.pojo.equifaxreport;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zsw on 2018/7/13 0013.
 */
@XStreamAlias("sch:InquiryPhone")
public class InquiryPhone implements Serializable {
    @XStreamAlias("sch:Number")
    private String number;
    @XStreamAlias("sch:PhoneType")
    private String phoneType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
}
