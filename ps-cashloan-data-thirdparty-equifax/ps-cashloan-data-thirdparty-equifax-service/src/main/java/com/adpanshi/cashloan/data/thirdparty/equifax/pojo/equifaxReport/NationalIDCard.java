package com.adpanshi.cashloan.data.thirdparty.equifax.pojo.equifaxreport;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("sch:NationalIDCard")
public class NationalIDCard  implements Serializable {
    @XStreamAlias("sch:IdNumber")
    private String idNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
