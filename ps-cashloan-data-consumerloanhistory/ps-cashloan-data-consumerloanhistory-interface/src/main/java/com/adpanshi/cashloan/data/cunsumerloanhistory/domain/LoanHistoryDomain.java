package com.adpanshi.cashloan.data.cunsumerloanhistory.domain;

import com.adpanshi.cashloan.data.cunsumerloanhistory.bo.LoanHistoryDataBo;

/**
 * Created by zsw on 2018/8/4 0004.
 */
public interface LoanHistoryDomain {

    /**
     * 添加app用户贷款历史原始数据
     */
    Integer addCunsumerLoanHistoryData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据ID获取app用户贷款历史原始数据
     * @param id app数据id
     */
    LoanHistoryDataBo getCunsumerLoanHistoryData(Integer id);
}
