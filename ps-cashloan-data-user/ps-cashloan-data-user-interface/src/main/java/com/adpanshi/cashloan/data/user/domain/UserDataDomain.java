package com.adpanshi.cashloan.data.user.domain;


import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;
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
     * 新增原始数据
     */
    void addMetaData(Integer userDataId, UserMetaDataBo userMetaDataBo);

    /**
     * 填充用户基本信息
     * @return 原始数据主键
     */
    Integer fillUserBaseInfo(Integer userDataId, String originalData);

    /**
     * 抽取变量
     *
     * @param userDataId     用户数据
     * @param channelType    渠道
     * @param channelBizType 渠道业务
     * @param channelDataId 原始数据ID
     */
    List<UserVariableBo> extractVariable(Integer userDataId, ChannelTypeEnum channelType, ChannelBizTypeEnum channelBizType, Integer channelDataId);


    /**
     * 抽取多个特征,全部成功返回true,任意一个失败返回false且不会保存到UserData中
     */
    Boolean extractFeatures(Integer userDataId, List<ExtractFeatureApplyBo> applyBoList);

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
}













