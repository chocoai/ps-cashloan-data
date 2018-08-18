package com.adpanshi.cashloan.data.thirdparty.equifax.pojo.equifaxreport;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsw on 2018/7/13 0013.
 */
public class InquiryRequestInfo implements Serializable {
    @XStreamAlias("sch:InquiryPurpose")
    private String inquiryPurpose;
    @XStreamAlias("sch:FirstName")
    private String firstName;
    @XStreamAlias("sch:LastName")
    private String lastName;
    @XStreamAlias("sch:AddrLine1")
    private String addrLine1;
    @XStreamAlias("sch:State")
    private String state;
    @XStreamAlias("sch:Postal")
    private String postal;
    @XStreamAlias("sch:InquiryAddresses")
    private List<InquiryAddress> inquiryAddresses;
    @XStreamAlias("sch:InquiryPhones")
    private List<InquiryPhone> inquiryPhones;
    @XStreamAlias("sch:DOB")
    private String dob;
    @XStreamAlias("sch:PANId")
    private String panid;
    @XStreamAlias("sch:MobilePhone")
    private String mobilePhone;

    public String getInquiryPurpose() {
        return inquiryPurpose;
    }

    public void setInquiryPurpose(String inquiryPurpose) {
        this.inquiryPurpose = inquiryPurpose;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public List<InquiryAddress> getInquiryAddresses() {
        return inquiryAddresses;
    }

    public void setInquiryAddresses(List<InquiryAddress> inquiryAddresses) {
        this.inquiryAddresses = inquiryAddresses;
    }

    public List<InquiryPhone> getInquiryPhones() {
        return inquiryPhones;
    }

    public void setInquiryPhones(List<InquiryPhone> inquiryPhones) {
        this.inquiryPhones = inquiryPhones;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPanid() {
        return panid;
    }

    public void setPanid(String panid) {
        this.panid = panid;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
