package com.adpanshi.cashloan.data.thirdparty.equifax.domain;

import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 信用报告远程调用
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class EquifaxCreditReportRemoteDomain implements EquifaxCreditReportDomain {
    @Resource
    private EquifaxCreditReportNativeDomain equifaxCreditReportDomain;

    /**
     * 获取信用报告原始数据
     *
     * @param requestParams     请求参数
     * @return  原始数据ID
     */
    @Override
    public String sendRequest(String requestParams) {
        return equifaxCreditReportDomain.sendRequest(requestParams);
    }

    @Override
    public EquifaxReportDataBo getMetaData(Integer dataId) {
        return null;
    }

    @Override
    public Integer save(String requestParams, String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String uuid, String originalData) {
        return null;
    }

    /**
     * 添加equifax信用报告原始数据
     *
     * @param mobile
     * @param email
     * @param status
     * @param uuid
     * @param requestParams
     * @param originalData
     * @return
     */
    @Override
    public Integer saveLog(String mobile, String email, String status, String uuid, String requestParams, String originalData) {
        return null;
    }
}
