package com.adpanshi.cashloan.data.user.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.common.utils.CheckUtil;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataBo;
import com.adpanshi.cashloan.data.feature.domain.FeatureDomain;
import com.adpanshi.cashloan.data.user.bo.*;
import com.adpanshi.cashloan.data.user.pojo.*;
import com.adpanshi.cashloan.data.user.service.UserDataService;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户数据接口实现
 * Created by zsw on 2018/6/29 0029.
 */
@Service("userDataDomain")
public class UserDataNativeDomain implements UserDataDomain {

    @Resource
    private UserDataService userDataService;

    @Resource
    private AppDataDomain appDataNativeDomain;

    @Resource
    private VariableDomain variableDomain;

    @Resource
    private FeatureDomain featureDomain;
    /**
     * 创建用户数据
     */
    @Override
    public UserDataBo create(UserBaseDataBo userBaseDataBo) {
        return BeanUtil.convert(userDataService.add(BeanUtil.convert(userBaseDataBo, UserBaseData.class)), UserDataBo.class);
    }

    /**
     * 获取用户数据By主键
     */
    @Override
    public UserDataBo get(Integer userDataId) {
        return BeanUtil.convert(userDataService.get(userDataId), UserDataBo.class);
    }

    /**
     * 修改用户基本数据
     */
    @Override
    public void modify(Integer userDataId, UserBaseDataBo userBaseDataBo) {
        UserData userData = userDataService.get(userDataId);
        if (userData == null) {
            throw new BusinessException("修改用户数据异常,用户数据不存在,id:" + userDataId);
        }
        if (StringUtils.isNotEmpty(userBaseDataBo.getAadhaarNo()) && StringUtils.isEmpty(userData.getAadhaarNo())) {
            userData.setAadhaarNo(userBaseDataBo.getAadhaarNo());
        }
        if (StringUtils.isNotEmpty(userBaseDataBo.getMobile()) && StringUtil.isEmpty(userData.getMobile())) {
            userData.setMobile(userBaseDataBo.getMobile());
        }
        if (StringUtils.isNotEmpty(userBaseDataBo.getEmail()) && StringUtil.isEmpty(userData.getEmail())) {
            userData.setEmail(userBaseDataBo.getEmail());
        }
        if (StringUtils.isNotEmpty(userBaseDataBo.getDeviceFingerprint()) && StringUtil.isEmpty(userData.getDeviceFingerprint())) {
            userData.setDeviceFingerprint(userBaseDataBo.getDeviceFingerprint());
        }
        if (StringUtils.isNotEmpty(userBaseDataBo.getName()) && StringUtil.isEmpty(userData.getName())) {
            userData.setName(userBaseDataBo.getName());
        }
        userDataService.modify(userData);
    }

    @Override
    public void addMetaData(Integer userDataId, UserMetaDataBo userMetaDataBo) {
        //获取用户数据
        UserData userData = userDataService.get(userDataId);
        CheckUtil.checkNotNull(userData, "用户原始数据为空！userDataId = " + userDataId);
        //获取用户现有原始数据
        List<UserMetaData> userMetaDataList = userData.getUserMetaDataList();
        if(CheckUtil.checkListIsNullOrIsEmpty(userMetaDataList)) {
            userMetaDataList = new ArrayList<>();
            userData.setUserMetaDataList(userMetaDataList);
        }
        UserMetaData userMetaData = BeanUtil.convert(userMetaDataBo, UserMetaData.class);

        userMetaDataList.add(userMetaData);
        userDataService.modify(userData);
    }

    /**
     * 填充用户基本信息
     */
    @Override
    public Integer fillUserBaseInfo(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        Integer id = appDataNativeDomain.addAppData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);

        UserMetaDataBo userMetaDataBo = new UserMetaDataBo();
        userMetaDataBo.setChannelType(ChannelTypeEnum.PSAPP);
        userMetaDataBo.setChannelBizType(ChannelBizTypeEnum.APP_USER_BASE_INFO);
        userMetaDataBo.setChannelDataId(id);
        userMetaDataBo.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        this.addMetaData(userDataId, userMetaDataBo);
        return id;
    }

    /**
     * 抽取变量
     */
    @Override
    public List<UserVariableBo> extractVariable(Integer userDataId, ChannelTypeEnum channelType, ChannelBizTypeEnum channelBizType, Integer channelDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        //获取用户原始数据
        List<UserMetaData> userMetaDataList = userData.getUserMetaDataList();
        if(userMetaDataList == null){
            userMetaDataList = new ArrayList<>();
        }
        //遍历原始数据
        UserMetaData userMetaData = null;
        for (UserMetaData u1 : userMetaDataList) {
            //找到指定原始数据,退出
            if (channelType.getValue().equals(u1.getChannelType()) && channelBizType.getValue().equals(u1.getChannelBizType()) && u1.getChannelDataId().equals(channelDataId)) {
                userMetaData = u1;
                break;
            }
        }
        if (userMetaData == null) {
            throw new BusinessException("未获取到原始数据,查询参数：userDataId="+ userDataId +", channelType=" + channelType +
                    ", channelBizType=" + channelBizType + ",channelDataId=" + channelDataId);
        }
        //获取抽取到的变量
        List<VariableDataBo> resultBoList = variableDomain.extractVariable(channelType, channelBizType, channelDataId, userMetaData.getCreateTime(), userData.getMobile(), userData.getEmail(), userData.getAadhaarNo(), userData.getName(), userData.getDeviceFingerprint());
        //未抽取到变量,可能对应的变量配置器为空
        if (resultBoList == null || resultBoList.size() == 0) {
            throw new BusinessException("变量抽取跑了个空,元芳你怎么看？");
        }
        List<UserVariable> userVariableList = userData.getUserVariableList();
        if(userVariableList == null){
            userVariableList = new ArrayList<>();
            userData.setUserVariableList(userVariableList);
        }
        //添加变量结果
        for (VariableDataBo resultBo : resultBoList) {
            UserVariable userVariable = new UserVariable();
            userVariable.setVariableExtractVersion(resultBo.getVariableExtractVersion());
            userVariable.setVariableType(resultBo.getVariableType().getValue());
            userVariable.setVariableDataId(resultBo.getFid());
            userVariable.setUserVariableDataFrom(userMetaData);
            userVariable.setCreateTime(resultBo.getCreateTime());
            userVariableList.add(userVariable);
        }
        userDataService.modify(userData);
        return data2Bo(resultBoList);
    }

    private List<UserVariableBo> data2Bo(List<VariableDataBo> resultBoList){
        if(resultBoList == null || resultBoList.size() == 0){
            return null;
        }
        List<UserVariableBo> userVariableBoList = new ArrayList<>();
        for (VariableDataBo variableDataBo : resultBoList) {
            UserVariableBo userVariableBo = new UserVariableBo();
            userVariableBo.setVariableDataId(variableDataBo.getFid());
            userVariableBo.setVariableType(variableDataBo.getVariableType());
            userVariableBo.setVariableExtractVersion(variableDataBo.getVariableExtractVersion());
            userVariableBo.setCreateTime(variableDataBo.getCreateTime());
            userVariableBoList.add(userVariableBo);
        }
        return userVariableBoList;
    }
    /**
     * 抽取多个特征
     */
    @Override
    public Boolean extractFeatures(Integer userDataId, List<ExtractFeatureApplyBo> applyBoList) {
        //校验数据
        UserData userData = userDataService.get(userDataId);
        if (userData == null) {
            throw new BusinessException("用户数据信息为空,id：" + userDataId);
        }
        if (applyBoList == null || applyBoList.size() == 0) {
            throw new BusinessException("用户变量信息为空,id：" + userDataId);
        }
        List<UserVariable> userVariableList = userData.getUserVariableList();
        if (userVariableList == null || userVariableList.size() == 0) {
            throw new BusinessException("用户变量信息为空,id：" + userDataId);
        }
        for (ExtractFeatureApplyBo applyBo : applyBoList) {
            if (applyBo.getUserVariableIdList() == null || applyBo.getUserVariableIdList().size() == 0) {
                throw new BusinessException("抽取特征参数错误,userDataId：" + userDataId);
            }
        }
        //把用户数据信息下的所有变量集合放map中
        Map<Integer, UserVariable> variableMap = new HashMap<>();
        for (UserVariable userVariable : userVariableList) {
            variableMap.put(userVariable.getVariableDataId(), userVariable);
        }
        //抽取所有特征
        for (ExtractFeatureApplyBo applyBo : applyBoList) {
            //封装抽取特征入参
            List<DataFromBo> dataFromBoList = new ArrayList<>();
            for (Integer userVariableId : applyBo.getUserVariableIdList()) {
                UserVariable variable = variableMap.get(userVariableId);
                if (variable != null) {
                    DataFromBo dataFromBo = new DataFromBo();
                    dataFromBo.setVariableDataId(variable.getVariableDataId());
                    dataFromBo.setVariableType(VariableType.getByValue(variable.getVariableType()));
                    dataFromBo.setVariableExtractVersion(variable.getVariableExtractVersion());
                    dataFromBo.setCreateTime(variable.getCreateTime());
                    dataFromBo.setChannelDataCreateTime(variable.getUserVariableDataFrom().getCreateTime());
                    dataFromBoList.add(dataFromBo);
                }
            }
            //抽取特征
            FeatureDataBo featureExtractResultBo = featureDomain.extractFeature(dataFromBoList, applyBo.getFeatureType(), userData.getMobile(), userData.getAadhaarNo(), userData.getEmail(), userData.getName(), userData.getDeviceFingerprint());
            if (featureExtractResultBo == null) {
                throw new BusinessException("抽取特征为空");
            }
            UserFeature userFeature = convertEntity(featureExtractResultBo, userDataId, variableMap);
            userData.getUserFeatureList().add(userFeature);
        }
        userDataService.modify(userData);
        return Boolean.TRUE;
    }

    @Override
    public UserDataStatusBo innerCrossValidationPhone(Integer userDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        if (userData == null) {
            throw new BusinessException("用户数据信息为空,查询参数：" + userDataId);
        }
        //获取该用户变量集合
        List<UserVariable> userVariableList = userData.getUserVariableList();
        if (userVariableList == null || userVariableList.size() == 0) {
            throw new BusinessException("获取变量为空");
        }
        //获取到指定的变量
        UserVariable curUserVariable = null;
        String curUserVariableCreatedTime = null;
        for (UserVariable userVariable : userVariableList) {
            if (VariableType.USER_CONTACTS_INFO.getValue().equalsIgnoreCase(userVariable.getVariableType()) &&
                    ChannelBizTypeEnum.PS_CROSS_VALIDATION_PHONE.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
                if(curUserVariableCreatedTime == null || curUserVariableCreatedTime.compareTo(userVariable.getCreateTime())<0) {
                    curUserVariable = userVariable;
                    curUserVariableCreatedTime = userVariable.getCreateTime();
                }
            }
        }
        //没有获取到相关用户变量，直接修改任务状态为跳过
        if (curUserVariable == null) {
            return new UserDataStatusBo(UserDataStatusBo.DataStatus.SKIP, null);
        }
        VariableDataBo variableFindById = variableDomain.getVariableFindById(curUserVariable.getVariableDataId());
        //没有获取到相关用户变量，直接修改任务状态为跳过
        if (variableFindById == null) {
            return new UserDataStatusBo(UserDataStatusBo.DataStatus.SKIP, null);
        }
        //变量中抓取通话详单TOP10手机号
        List<VariableDataValueBo> valueList = variableFindById.getValueList();
        List<String> phones = new ArrayList<>();
        for (VariableDataValueBo variableDataValueBo : valueList) {
            if ("nearly6MonthsCallRecordTop10DirectContactList".equalsIgnoreCase(variableDataValueBo.getKey())) {// TODO 通话记录前十key值自己定
                JSONArray jsonArray = (JSONArray) variableDataValueBo.getValue();
                for (Object o : jsonArray) {
                    phones.add((String) o);
                }
            }
        }
        //TODO 历史贷款用户手机号匹配
//        Integer id = consumerLoanHistoryNaiveDomain.sendPhoneInfoReq(phones);
//        if (id == null) {
//            return null;
//        }
        //TODO 保存原始数据
        Integer id = 1;
        this.addMetaData(userDataId, new UserMetaDataBo(ChannelTypeEnum.CONSUMERLOANHISTORY, ChannelBizTypeEnum.CONSUMERLOANHISTORY_LOAN_RECORD, id,
                DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat)));
        return new UserDataStatusBo(UserDataStatusBo.DataStatus.SUCCESS, id);
    }

    @Override
    public Integer innerCrossValidationMaster(Integer userDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);

        //TODO 保存原始数据
        Integer id = 1;//consumerLoanHistoryNaiveDomain.sendMasterInfoReq(userData.getName(), userData.getIdCard());
        if (id == null) {
            throw new BusinessException("交叉验证本人信息返回ID为空！");
        }
        this.addMetaData(userDataId, new UserMetaDataBo(ChannelTypeEnum.CONSUMERLOANHISTORY,
                ChannelBizTypeEnum.PS_CROSS_VALIDATION_MASTER, id, DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat)));
        return id;
    }

    @Override
    public UserDataStatusBo innerCrossValidationContacts(Integer userDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        if (userData == null) {
            throw new BusinessException("用户数据信息为空,查询参数：" + userDataId);
        }
        //获取该用户变量集合
        List<UserVariable> userVariableList = userData.getUserVariableList();
        if (userVariableList == null || userVariableList.size() == 0) {
            throw new BusinessException("获取变量为空");
        }
        //获取到指定的变量
        UserVariable curUserVariable = null;
        String curUserVariableCreatedTime = null;
        for (UserVariable userVariable : userVariableList) {
            if (VariableType.USER_CONTACTS_INFO.getValue().equalsIgnoreCase(userVariable.getVariableType()) &&
                    ChannelBizTypeEnum.PS_COMPLEX.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
                if(curUserVariableCreatedTime == null || curUserVariableCreatedTime.compareTo(userVariable.getCreateTime())<0) {
                    curUserVariable = userVariable;
                    curUserVariableCreatedTime = userVariable.getCreateTime();
                }
            }
        }
        if (curUserVariable == null) {
            return new UserDataStatusBo(UserDataStatusBo.DataStatus.SKIP, null);
        }
        VariableDataBo variableFindById = variableDomain.getVariableFindById(curUserVariable.getVariableDataId());
        if (variableFindById == null) {
            return new UserDataStatusBo(UserDataStatusBo.DataStatus.SKIP, null);
        }
        //变量中抓取通话详单TOP10手机号
        List<VariableDataValueBo> valueList = variableFindById.getValueList();
        List<String> phones = new ArrayList<>();
        for (VariableDataValueBo variableDataValueBo : valueList) {
            if ("contactsNameAndPhoneNumber".equalsIgnoreCase(variableDataValueBo.getKey())) {
                List<JSONObject> contactsInfos = (List<JSONObject>) variableDataValueBo.getValue();
                for (JSONObject jsonObject : contactsInfos) {
                    if(jsonObject.containsKey("mobile")){
                        phones.add(jsonObject.getString("mobile"));

                    }
                }
            }
        }
        //TODO 保存原始数据
        Integer id = 1;//consumerLoanHistoryNaiveDomain.sendPhoneInfoReq(phones);
        if (id == null) {
            return null;
        }
        this.addMetaData(userDataId, new UserMetaDataBo(ChannelTypeEnum.CONSUMERLOANHISTORY, ChannelBizTypeEnum.PS_CROSS_VALIDATION_CONTACT, id,
                DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat)));
        return new UserDataStatusBo(UserDataStatusBo.DataStatus.SUCCESS, id);
    }

    @Override
    public UserDataStatusBo innerCrossValidationEmergency1(Integer userDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        if (userData == null) {
            throw new BusinessException("用户数据信息为空,查询参数：" + userDataId);
        }
        //获取该用户变量集合
        List<UserVariable> userVariableList = userData.getUserVariableList();
        if (userVariableList == null || userVariableList.size() == 0) {
            throw new BusinessException("获取变量为空");
        }
        //获取到指定的变量
        UserVariable curUserVariable = null;
        String curUserVariableCreatedTime = null;
        for (UserVariable userVariable : userVariableList) {
            if (VariableType.USER_FASTTOUCH.getValue().equalsIgnoreCase(userVariable.getVariableType()) &&
                    ChannelBizTypeEnum.PS_CROSS_VALIDATION_EMERGENCY1.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
                if(curUserVariableCreatedTime == null || curUserVariableCreatedTime.compareTo(userVariable.getCreateTime())<0) {
                    curUserVariable = userVariable;
                    curUserVariableCreatedTime = userVariable.getCreateTime();
                }
            }
        }
        if (curUserVariable == null) {
            return new UserDataStatusBo(UserDataStatusBo.DataStatus.SKIP, null);
        }
        VariableDataBo variableFindById = variableDomain.getVariableFindById(curUserVariable.getVariableDataId());
        if (variableFindById == null) {
            return new UserDataStatusBo(UserDataStatusBo.DataStatus.SKIP, null);
        }
        //变量中抓取通话详单TOP10手机号
        List<VariableDataValueBo> valueList = variableFindById.getValueList();
        List<String> phones = new ArrayList<>();
        for (VariableDataValueBo variableDataValueBo : valueList) {
            if ("appUserEmergencyContactPhoneNumber".equalsIgnoreCase(variableDataValueBo.getKey())) {
                String phone = (String) variableDataValueBo.getValue();
                phones.add(phone);
            }
        }
//        Integer id = consumerLoanHistoryNaiveDomain.sendPhoneInfoReq(phones);
//        if (id == null) {
//            return null;
//        }
        //TODO 保存原始数据
        Integer id = 1;//consumerLoanHistoryNaiveDomain.sendPhoneInfoReq(phones);
        if (id == null) {
            return null;
        }
        this.addMetaData(userDataId, new UserMetaDataBo(ChannelTypeEnum.CONSUMERLOANHISTORY, ChannelBizTypeEnum.PS_CROSS_VALIDATION_EMERGENCY1, id,
                DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat)));

        return new UserDataStatusBo(UserDataStatusBo.DataStatus.SUCCESS, null);
    }

    /**
     * 银联画像
     */
    @Override
    public Integer applyBankPortrait(Integer userDataId, String cardNo) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        if (userData == null) {
            throw new BusinessException("用户数据信息为空,查询参数：" + userDataId);
        }
//        Integer bankPortraitId = bankPortraitDomain.sendBankPortraitReq(userData.getName(), userData.getIdCard(), userData.getMobile(), cardNo);
//        if (bankPortraitId == null) {
//            throw new BusinessException("获取银联画像信息ID为空");
//        }
//        userDataService.addChannelData(userDataId, bankPortraitId, ChannelTypeEnum.PSAPP.getValue(), ChannelBizTypeEnum.BT_BANK_PORTRAIT.getValue());
//        return bankPortraitId;
        return null;
    }

    @Override
    public List<Integer> findAddressCrossValidationVariableId(Integer userDataId) {
        UserDataBo userDataBo = get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }

        List<UserVariableBo> userVariableList = userDataBo.getUserVariableList();
        if(userVariableList == null || userVariableList.size() == 0){
            throw new BusinessException("用户变量信息为空");
        }
        //list按最新时间排序
//        sort(userVariableList);

        //取这些变量集合最新的数据
        List<Integer> variableIdList = new ArrayList<>();
        for (UserVariableBo userVariableBo : userVariableList) {
            if(userVariableBo.getVariableType().getValue().equalsIgnoreCase("USER_BASICINFO")){
                variableIdList.add(userVariableBo.getVariableDataId());
                break;
            }
        }
        for (UserVariableBo userVariableBo : userVariableList) {
            if(userVariableBo.getVariableType().getValue().equalsIgnoreCase("USER_CONTACTS_INFO")){
                variableIdList.add(userVariableBo.getVariableDataId());
                break;
            }
        }
        return variableIdList;
    }

    @Override
    public Integer findContactsPhonesByUserDataId(Integer userDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        if (userData == null) {
            throw new BusinessException("用户数据信息为空,查询参数：" + userDataId);
        }
        //获取该用户变量集合
        List<UserVariable> userVariableList = userData.getUserVariableList();
        if (userVariableList == null || userVariableList.size() == 0) {
            throw new BusinessException("获取变量为空");
        }
        //获取到指定的变量
        UserVariable curUserVariable = null;
        String curUserVariableCreatedTime = null;
        for (UserVariable userVariable : userVariableList) {
            if (VariableType.USER_CONTACTS_INFO.getValue().equalsIgnoreCase(userVariable.getVariableType()) &&
                    ChannelBizTypeEnum.PS_CROSS_VALIDATION_MASTER.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
                if(curUserVariableCreatedTime == null || curUserVariableCreatedTime.compareTo(userVariable.getCreateTime())<0) {
                    curUserVariable = userVariable;
                    curUserVariableCreatedTime = userVariable.getCreateTime();
                }
            }
        }
        if (curUserVariable == null) {
            return null;
        }
        return curUserVariable.getVariableDataId();
    }

    /**
     * FeatureDataBo 转成 UserFeature
     */
    private UserFeature convertEntity(FeatureDataBo featureDataBo, Integer userDataId, Map<Integer, UserVariable> variableMap) {
        UserFeature userFeature = new UserFeature();
        userFeature.setFeatureExtractVersion(featureDataBo.getFeatureExtractVersion());
        userFeature.setFeatureType(featureDataBo.getFeatureType().getValue());
        userFeature.setFeatureDataId(featureDataBo.getFid());
        userFeature.setFromUserDataId(userDataId);
        userFeature.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        List<UserVariable> variableList = new ArrayList<>();
        for (DataFromBo dataFromBo : featureDataBo.getDataFromList()) {
            variableList.add(variableMap.get(dataFromBo.getVariableDataId()));
        }
        userFeature.setUserFeatureDataFromList(variableList);
        return userFeature;
    }
}
