package com.adpanshi.cashloan.data.user.domain;

import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;
import com.adpanshi.cashloan.data.user.bo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class UserDataRemoteDomain implements UserDataDomain{

    @Resource
    private UserDataNativeDomain userDataDomain;


    @Override
    public UserDataBo create(UserBaseDataBo userBaseDataBo) {
        return userDataDomain.create(userBaseDataBo);
    }

    @Override
    public UserDataBo get(Integer userDataId) {
        return userDataDomain.get(userDataId);
    }

    @Override
    public void modify(Integer userDataId, UserBaseDataBo userBaseDataBo) {
        userDataDomain.modify(userDataId, userBaseDataBo);
    }

    @Override
    public void addMetaData(Integer userDataId, UserMetaDataBo userMetaDataBo) {
        userDataDomain.addMetaData(userDataId, userMetaDataBo);
    }

    @Override
    public Integer fillUserBaseInfo(Integer userDataId, String originalData) {
        return userDataDomain.fillUserBaseInfo(userDataId, originalData);
    }

    @Override
    public List<UserVariableBo> extractVariable(Integer userDataId, ChannelTypeEnum channelType, ChannelBizTypeEnum channelBizType, Integer channelDataId) {
        return userDataDomain.extractVariable(userDataId, channelType, channelBizType, channelDataId);
    }

    @Override
    public Boolean extractFeatures(Integer userDataId, List<ExtractFeatureApplyBo> applyBoList) {
        return userDataDomain.extractFeatures(userDataId, applyBoList);
    }

    @Override
    public UserDataStatusBo innerCrossValidationPhone(Integer userDataId) {
        return userDataDomain.innerCrossValidationPhone(userDataId);
    }

    @Override
    public Integer innerCrossValidationMaster(Integer userDataId) {
        return userDataDomain.innerCrossValidationMaster(userDataId);
    }

    @Override
    public UserDataStatusBo innerCrossValidationContacts(Integer userDataId) {
        return userDataDomain.innerCrossValidationContacts(userDataId);
    }

    @Override
    public UserDataStatusBo innerCrossValidationEmergency1(Integer userDataId) {
        return userDataDomain.innerCrossValidationEmergency1(userDataId);
    }

    @Override
    public Integer applyBankPortrait(Integer userDataId, String cardNo) {
        return userDataDomain.applyBankPortrait(userDataId, cardNo);
    }

    @Override
    public List<Integer> findAddressCrossValidationVariableId(Integer userDataId) {
        return userDataDomain.findAddressCrossValidationVariableId(userDataId);
    }

    @Override
    public Integer findContactsPhonesByUserDataId(Integer userDataId) {
        return userDataDomain.findContactsPhonesByUserDataId(userDataId);
    }

}
