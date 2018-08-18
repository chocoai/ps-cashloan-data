package com.adpanshi.cashloan.data.user.domain;


import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;
import com.adpanshi.cashloan.data.user.bo.*;

import java.util.List;

/**
 * 用户主体数据
 * Created by zsw on 2018/6/29 0029.
 */
public interface UserDataDomain {

    /**
     * 创建用户数据
     */
    UserDataBo create(UserBaseDataBo userBaseDataBo);

    /**
     * 获取用户数据By主键
     */
    UserDataBo get(Integer userDataId);


    /**
     * 修改用户数据
     */
    void modify(Integer userDataId, UserBaseDataBo userBaseDataBo);

    /**
     * 填充用户基本信息
     * @return 原始数据主键
     */
    Integer fillUserBaseInfo(Integer userDataId, String originalData);

    /**
     * 填充用户通讯录
     * @param userDataId
     * @param originalData
     * @return
     */
    Integer fillCommunication(Integer userDataId, String originalData);

    /**
     * 填充app应用
     * @param userDataId
     * @param originalData
     * @return
     */
    Integer fillApplication(Integer userDataId, String originalData);
    /**
     * 填充app紧急联系人
     * @param userDataId
     * @param originalData
     * @return
     */
    Integer fillEmergency(Integer userDataId, String originalData);

    /**
     * 抽取变量
     *
     * @param userDataId     用户数据
     * @param channelType    渠道
     * @param channelBizType 渠道业务
     * @param channelDataId 原始数据ID
     */
    List<UserVariableBo> extractVariable(Integer userDataId, ChannelType channelType, ChannelBizType channelBizType, Integer channelDataId);


    /**
     * 抽取多个特征,全部成功返回true,任意一个失败返回false且不会保存到UserData中
     */
    Boolean extractFeatures(Integer userDataId, List<ExtractFeatureApplyBo> applyBoList);

    /**
     *
     * @param userDataId    用户数据ID
     * @param requestParams 盘卡号
     * @return
     */
    PanCardDataBo fillPanCardInfo(Integer userDataId, String requestParams);

    /**
     *  保存历史数据
     * @param userDataId    用户数据ID
     * @param requestParams 盘卡号
     * @return
     */
    PanCardDataBo fillPanCardInfoForOldData(Integer userDataId, String requestParams);

    /**
     * 获取equifax信用报告原始数据
     * @param requestParam  请求参数
     * @return  原始数据
     */
    String getEquifaxCreditReportMetaData(String requestParam);
    /**
     * 用户报告
     * @param userDataId    用户数据ID
     * @param params        请求参数
     * @return
     */
    Integer fillEquifaxCreditReportInfo(Integer userDataId, String uuid, String params, String metaData);

    /**
     * 日志记录
     * @param userDataId
     * @param params
     * @param metaData
     * @return
     */
    Integer saveEquifaxCreditReportLog(Integer userDataId, String status, String uuid, String params, String metaData);

    /**
     * 内部交叉验证,手机号校验联系人
     * @return 原始数据主键
     */
    UserDataStatusBo innerCrossValidationPhone(Integer userDataId);

    /**
     * 内部交叉验证,本人信息校验
     * @return 原始数据主键
     */
    Integer innerCrossValidationMaster(Integer userDataId);

    /**
     * 内部交叉验证，通讯录信息校验
     * @param userDataId
     * @return 原始数据主键
     */
    UserDataStatusBo innerCrossValidationContacts(Integer userDataId);

    /**
     * 内部交叉验证，紧急联系人信息校验
     * @param userDataId
     * @return 原始数据主键
     */
    UserDataStatusBo innerCrossValidationEmergency1(Integer userDataId);

    /**
     * 银联画像信息获取
     * @param cardNo 卡号
     * @return 查询ID
     */
    Integer applyBankPortrait(Integer userDataId , String cardNo);

    /**
     * 地址交叉验证
     * @param userDataId
     * @return
     */
    List<Integer> findAddressCrossValidationVariableId(Integer userDataId);

    /**
     * 通过用户数据ID获取通讯录手机号集合
     * @param userDataId
     * @return
     */
    Integer findContactsPhonesByUserDataId(Integer userDataId);

    /**
     * oloan贷款申请
     * @param userDataId    用户数据ID
     * @return  //贷款记录原始数据ID  ArcBorrowRuleResult 机审结果
     */
    String oloanLoanApply(Integer userDataId);

    /**
     * 填充贷款申请信息
     * @param userDataId    用户数据ID
     * @param originalData  贷款决策建议
     * @return  原始数据ID
     */
    Integer fillLoanApplyInfo(Integer userDataId, String originalData);
    /**
     * 填充SIM卡信息
     * @return 原始数据主键
     */
    Integer fillSimInfo(Integer userDataId, String simMetaData);

    /**
     * 请求磨盒获取社交信息
     * @param requestParam  请求参数
     * @return  返回结果
     */
    String getMoxieSNSInfoFromThirdParty(String requestParam);
    /**
     * 填充SNS信息
     * @param userDataId    用户数据ID
     * @param snsMetaData   用户社交信息原始数据
     * @return  原始数据ID
     */
    Integer fillSNSInfo(Integer userDataId, String snsMetaData);
    /**
     * 请求同盾保镖获取社交信息
     * @param requestParam  请求参数
     * @return  返回结果
     */
    String getTDBodyGuardInfoFromThirdParty(String requestParam);
    /**
     * 填充SNS信息
     * @param userDataId    用户数据ID
     * @param tdBodyGuardMetaData   用户社交信息原始数据
     * @return  原始数据ID
     */
    Integer fillTDBodyGuardInfo(Integer userDataId, String tdBodyGuardMetaData);


    /**
     * 填充通话记录信息数据
     * @param userDataId 用户数据id
     * @param callRecordMetaData 用户通话记录信息原始数据
     * @return
     */
    Integer fillCallRecordInfo(Integer userDataId,String callRecordMetaData);

}













