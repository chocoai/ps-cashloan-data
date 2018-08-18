package com.adpanshi.cashloan.data.thirdparty.equifax.domain;

import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;

/**
 * Created by zsw on 2018/8/2 0002.
 * @author 8452
 */
public interface EquifaxCreditReportDomain {
    /**
     * 获取信用报告信息
     * @param requestParams
     * @return
     */
    String sendRequest(String requestParams);

    /**
     * 获取信用报告原始数据By主键
     * @param dataId    信用报告数据ID
     */
    EquifaxReportDataBo getMetaData(Integer dataId);

    /**
     *添加equifax信用报告原始数据
     */
    Integer save(String requestParams, String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String uuid, String originalData);

    /**
     *添加equifax信用报告原始数据
     * @param mobile
     * @param email
     * @param status
     * @param uuid
     * @param requestParams
     * @param originalData
     * @return
     */
    Integer saveLog(String mobile, String email, String status, String uuid, String requestParams, String originalData);
}
