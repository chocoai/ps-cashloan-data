package com.adpanshi.cashloan.data.user.domain;

import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;
import com.adpanshi.cashloan.data.user.bo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户数据接口
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class UserDataRemoteDomain implements UserDataDomain{

    @Resource
    private UserDataNativeDomain userDataNativeDomain;


    @Override
    public UserDataBo create(UserBaseDataBo userBaseDataBo) {
        return userDataNativeDomain.create(userBaseDataBo);
    }

    @Override
    public UserDataBo get(Integer userDataId) {
        return userDataNativeDomain.get(userDataId);
    }

    @Override
    public void modify(Integer userDataId, UserBaseDataBo userBaseDataBo) {
        userDataNativeDomain.modify(userDataId, userBaseDataBo);
    }

    @Override
    public Integer fillUserBaseInfo(Integer userDataId, String originalData) {
        return userDataNativeDomain.fillUserBaseInfo(userDataId, originalData);
    }

    /**
     * 填充用户通讯录
     *
     * @param userDataId
     * @param originalData
     * @return
     */
    @Override
    public Integer fillCommunication(Integer userDataId, String originalData) {
        return userDataNativeDomain.fillCommunication(userDataId, originalData);
    }

    /**
     * 填充app应用
     *
     * @param userDataId
     * @param originalData
     * @return
     */
    @Override
    public Integer fillApplication(Integer userDataId, String originalData) {
        return userDataNativeDomain.fillApplication(userDataId, originalData);
    }
    /**
     * 填充app紧急联系人
     *
     * @param userDataId
     * @param originalData
     * @return
     */
    @Override
    public Integer fillEmergency(Integer userDataId, String originalData) {
        return userDataNativeDomain.fillEmergency(userDataId,originalData);
    }

    @Override
    public List<UserVariableBo> extractVariable(Integer userDataId, ChannelType channelType, ChannelBizType channelBizType, Integer channelDataId) {
        return userDataNativeDomain.extractVariable(userDataId, channelType, channelBizType, channelDataId);
    }

    @Override
    public Boolean extractFeatures(Integer userDataId, List<ExtractFeatureApplyBo> applyBoList) {
        return userDataNativeDomain.extractFeatures(userDataId, applyBoList);
    }

    @Override
    public PanCardDataBo fillPanCardInfo(Integer userDataId, String requestParams) {
        return userDataNativeDomain.fillPanCardInfo(userDataId, requestParams);
    }

    @Override
    public PanCardDataBo fillPanCardInfoForOldData(Integer userDataId, String requestParams) {
        return userDataNativeDomain.fillPanCardInfoForOldData(userDataId, requestParams);
    }

    @Override
    public String getEquifaxCreditReportMetaData(String requestParam) {
        return null;
    }

    /**
     * 用户报告
     *
     * @param userDataId 用户数据ID
     * @param uuid
     * @param params     请求参数
     * @param metaData   @return
     */
    @Override
    public Integer fillEquifaxCreditReportInfo(Integer userDataId, String uuid, String params, String metaData) {
        return null;
    }

    /**
     * 日志记录
     *
     * @param userDataId
     * @param status
     * @param uuid
     * @param params
     * @param metaData   @return
     */
    @Override
    public Integer saveEquifaxCreditReportLog(Integer userDataId, String status, String uuid, String params, String metaData) {
        return null;
    }

    @Override
    public UserDataStatusBo innerCrossValidationPhone(Integer userDataId) {
        return userDataNativeDomain.innerCrossValidationPhone(userDataId);
    }

    @Override
    public Integer innerCrossValidationMaster(Integer userDataId) {
        return userDataNativeDomain.innerCrossValidationMaster(userDataId);
    }

    @Override
    public UserDataStatusBo innerCrossValidationContacts(Integer userDataId) {
        return userDataNativeDomain.innerCrossValidationContacts(userDataId);
    }

    @Override
    public UserDataStatusBo innerCrossValidationEmergency1(Integer userDataId) {
        return userDataNativeDomain.innerCrossValidationEmergency1(userDataId);
    }

    @Override
    public Integer applyBankPortrait(Integer userDataId, String cardNo) {
        return userDataNativeDomain.applyBankPortrait(userDataId, cardNo);
    }

    @Override
    public List<Integer> findAddressCrossValidationVariableId(Integer userDataId) {
        return userDataNativeDomain.findAddressCrossValidationVariableId(userDataId);
    }

    @Override
    public Integer findContactsPhonesByUserDataId(Integer userDataId) {
        return userDataNativeDomain.findContactsPhonesByUserDataId(userDataId);
    }

    @Override
    public String oloanLoanApply(Integer userDataId) {
        return userDataNativeDomain.oloanLoanApply(userDataId);
    }

    @Override
    public Integer fillLoanApplyInfo(Integer userDataId, String originalData) {
        return userDataNativeDomain.fillLoanApplyInfo(userDataId, originalData);
    }

    @Override
    public Integer fillSimInfo(Integer userDataId, String simMetaData) {
        return userDataNativeDomain.fillSimInfo(userDataId, simMetaData);
    }

    @Override
    public String getMoxieSNSInfoFromThirdParty(String requestParam) {
        return userDataNativeDomain.getMoxieSNSInfoFromThirdParty(requestParam);
    }

    @Override
    public Integer fillSNSInfo(Integer userDataId, String snsMetaData) {
        return userDataNativeDomain.fillSNSInfo(userDataId, snsMetaData);
    }

    @Override
    public Integer fillCallRecordInfo(Integer userDataId, String callRecordMetaData) {
        return userDataNativeDomain.fillCallRecordInfo(userDataId,callRecordMetaData);
    }

    @Override
    public String getTDBodyGuardInfoFromThirdParty(String requestParam) {
        return userDataNativeDomain.getTDBodyGuardInfoFromThirdParty(requestParam);
    }

    @Override
    public Integer fillTDBodyGuardInfo(Integer userDataId, String tdBodyGuardMetaData) {
        return userDataNativeDomain.fillTDBodyGuardInfo(userDataId, tdBodyGuardMetaData);
    }

}
