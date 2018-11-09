package com.adpanshi.cashloan.data.user.domain;

import com.adpanshi.cashloan.data.appdata.bo.AppApplicationDataBo;
import com.adpanshi.cashloan.data.appdata.bo.AppCallRecordDataBo;
import com.adpanshi.cashloan.data.appdata.bo.AppCommunicationDataBo;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSNSBo;
import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;
import com.adpanshi.cashloan.data.user.bo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户数据接口
 * Created by zsw on 2018/6/29 0029.
 */
@Service("userDataDomain")
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

    @Override
    public AppCommunicationDataBo getAppCommunicationData(Integer dataId) {
        return userDataNativeDomain.getAppCommunicationData(dataId);
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

    @Override
    public AppApplicationDataBo getAppApplicationData(Integer dataId) {
        return userDataNativeDomain.getAppApplicationData(dataId);
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
    public String getEquifaxMetaDataFromThirdparty(String requestParam) {
        return userDataNativeDomain.getEquifaxMetaDataFromThirdparty(requestParam);
    }

    @Override
    public EquifaxReportDataBo getEquifaxMetaDataFromDB(Integer dataId) {
        return userDataNativeDomain.getEquifaxMetaDataFromDB(dataId);
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
        return userDataNativeDomain.fillEquifaxCreditReportInfo(userDataId, uuid, params, metaData);
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
        return userDataNativeDomain.saveEquifaxCreditReportLog(userDataId, status, uuid, params, metaData);
    }

    @Override
    public MoxieSIMBo getMoxieSIMMetaData(Integer dataId) {
        return userDataNativeDomain.getMoxieSIMMetaData(dataId);
    }

    @Override
    public MoxieSNSBo getMoxieSNSMetaData(Integer dataId) {
        return userDataNativeDomain.getMoxieSNSMetaData(dataId);
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
    public AppCallRecordDataBo getAppCallRecordData(Integer dataId) {
        return userDataNativeDomain.getAppCallRecordData(dataId);
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
