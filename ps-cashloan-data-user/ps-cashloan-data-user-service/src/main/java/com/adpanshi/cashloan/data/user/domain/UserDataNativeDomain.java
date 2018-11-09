package com.adpanshi.cashloan.data.user.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.bo.AppApplicationDataBo;
import com.adpanshi.cashloan.data.appdata.bo.AppCallRecordDataBo;
import com.adpanshi.cashloan.data.appdata.bo.AppCommunicationDataBo;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.cunsumerloanhistory.domain.LoanHistoryDomain;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataValueBo;
import com.adpanshi.cashloan.data.feature.domain.FeatureDomain;
import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;
import com.adpanshi.cashloan.data.thirdparty.equifax.domain.EquifaxCreditReportDomain;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSNSBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.domain.MoxieSIMDomain;
import com.adpanshi.cashloan.data.thirdparty.moxie.domain.MoxieSNSDomain;
import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;
import com.adpanshi.cashloan.data.thirdparty.pancard.domain.PanCardDomain;
import com.adpanshi.cashloan.data.thirdparty.tdbody.domain.TDBodyGuardDomain;
import com.adpanshi.cashloan.data.user.bo.*;
import com.adpanshi.cashloan.data.user.pojo.*;
import com.adpanshi.cashloan.data.user.service.UserDataService;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import com.adpanshi.cashloan.decision.bo.ArcBorrowRuleResultBo;
import com.adpanshi.cashloan.decision.domain.DecisionDomain;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户数据接口实现
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class UserDataNativeDomain implements UserDataDomain {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataNativeDomain.class);

    @Resource
    private UserDataService userDataService;

    @Resource
    private AppDataDomain appDataDomain;

    @Resource
    private VariableDomain variableDomain;

    @Resource
    private FeatureDomain featureDomain;

    @Resource
    private PanCardDomain panCardDomain;

    @Resource
    private DecisionDomain decisionRemoteDomain;

    @Resource
    private LoanHistoryDomain loanHistoryDomain;

    @Resource
    private EquifaxCreditReportDomain equifaxCreditReportDomain;

    @Resource
    private MoxieSIMDomain moxieSIMDomain;

    @Resource
    private MoxieSNSDomain moxieSNSDomain;

    @Resource
    private TDBodyGuardDomain tdBodyGuardDomain;
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

    /**
     * 填充用户基本信息
     */
    @Override
    public Integer fillUserBaseInfo(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        Integer id = appDataDomain.addAppUserBaseInfoData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.PSAPP.getValue(), ChannelBizType.APP_USER_BASE_INFO.getValue());
        return id;
    }

    /**
     * 填充用户通讯录
     *
     * @param userDataId    用户数据ID
     * @param originalData  原始数据
     * @return  原始数据ID
     */
    @Override
    public Integer fillCommunication(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        Integer id = appDataDomain.addAppCommunicationData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.PSAPP.getValue(), ChannelBizType.APP_COMMUNICATION.getValue());
        return id;
    }

    @Override
    public AppCommunicationDataBo getAppCommunicationData(Integer dataId) {
        return appDataDomain.getAppCommunicationData(dataId);
    }

    /**
     * 填充app应用
     *
     * @param userDataId    用户数据ID
     * @param originalData  原始数据
     * @return  原始数据ID
     */
    @Override
    public Integer fillApplication(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        Integer id = appDataDomain.addAppApplicationData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.PSAPP.getValue(), ChannelBizType.APP_APPLICATION.getValue());
        return id;
    }

    @Override
    public AppApplicationDataBo getAppApplicationData(Integer dataId) {
        return appDataDomain.getAppApplicationData(dataId);
    }

    /**
     * 填充app紧急联系人
     *
     * @param userDataId    用户数据ID
     * @param originalData  原始数据
     * @return  原始数据ID
     */
    @Override
    public Integer fillEmergency(Integer userDataId, String originalData) {
        //保存用户紧急联系人原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        Integer id = appDataDomain.addAppEmergencyData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.PSAPP.getValue(), ChannelBizType.APP_EMERGENCY.getValue());
        return id;
    }

    /**
     * 抽取变量
     */
    @Override
    public List<UserVariableBo> extractVariable(Integer userDataId, ChannelType channelType, ChannelBizType channelBizType, Integer channelDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        //获取用户原始数据
        List<UserMetaData> userMetaDataList = userData.getUserMetaDataList();
        if(userMetaDataList == null){
            userMetaDataList = new ArrayList<>();
        }
        //原始数据按照最新时间倒叙排列
        descSort(userMetaDataList);
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

    /**
     * 原始数据倒叙排列
     * @param userMetaDataList    原始数据集合
     */
    private void descSort(List<UserMetaData> userMetaDataList){
        Collections.sort(userMetaDataList, new Comparator<UserMetaData>(){
            @Override
            public int compare(UserMetaData o1, UserMetaData o2) {
                Date date2 = DateUtil.string2Date(o2.getCreateTime(), DateUtil.ymdhmsSSSFormat);
                Date date1 = DateUtil.string2Date(o1.getCreateTime(), DateUtil.ymdhmsSSSFormat);
                return date2.compareTo(date1);
            }
        });
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
        Map<Integer, UserVariable> variableMap = new HashMap<>(16);
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
    public PanCardDataBo fillPanCardInfo(Integer userDataId, String requestParams) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        PanCardDataBo panCardDataBo = panCardDomain.getPanInfo(userDataBo.getName(),userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), requestParams);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, panCardDataBo.getFid(), ChannelType.PANCARD.getValue(), ChannelBizType.PAN_CARD_VERIFY.getValue());
        return panCardDataBo;
    }

    @Override
    public PanCardDataBo fillPanCardInfoForOldData(Integer userDataId, String requestParams) {
        //保存用户数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        PanCardDataBo panCardDataBo = panCardDomain.getPanInfoForOldData( userDataBo.getName(),userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), requestParams);

        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, panCardDataBo.getFid(), ChannelType.PANCARD.getValue(), ChannelBizType.PAN_CARD_VERIFY.getValue());
        return panCardDataBo;
    }

    @Override
    public String getEquifaxMetaDataFromThirdparty(String requestParam) {
        return equifaxCreditReportDomain.sendRequest(requestParam);
    }

    @Override
    public EquifaxReportDataBo getEquifaxMetaDataFromDB(Integer dataId) {
        return equifaxCreditReportDomain.getMetaData(dataId);
    }

    /**
     * 用户报告
     *
     * @param userDataId 用户数据ID
     * @return  原始数据ID
     */
    @Override
    public Integer fillEquifaxCreditReportInfo(Integer userDataId, String uuid, String params, String metaData) {
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        Integer dataId = equifaxCreditReportDomain.save(params, userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), uuid, metaData);
        if (dataId == null) {
            return null;
        }
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, dataId, ChannelType.EQUIFAXREPORT.getValue(), ChannelBizType.EQUIFAX_REPORT_ANALYZE.getValue());
        return dataId;
    }

    /**
     * 日志记录
     *
     * @param userDataId    用户数据ID
     * @param params    请求参数
     * @param metaData  原始数据
     * @return  日志ID
     */
    @Override
    public Integer saveEquifaxCreditReportLog(Integer userDataId, String status, String uuid, String params, String metaData) {
        UserDataBo userDataBo = this.get(userDataId);
        Integer dataId = equifaxCreditReportDomain.saveLog(userDataBo.getMobile(), userDataBo.getEmail(), status, uuid, params, metaData);
        if (dataId == null) {
            return null;
        }
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, dataId, ChannelType.EQUIFAXREPORT.getValue(), ChannelBizType.EQUIFAX_REPORT_ANALYZE.getValue());
        return dataId;
    }

    @Override
    public MoxieSIMBo getMoxieSIMMetaData(Integer dataId) {
        return moxieSIMDomain.getMoxieSIMMetaData(dataId);
    }

    @Override
    public MoxieSNSBo getMoxieSNSMetaData(Integer dataId) {
        return moxieSNSDomain.getMoxieSNSMetaData(dataId);
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
                    ChannelBizType.CROSS_VALIDATION_PHONE.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
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
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.CONSUMERLOANHISTORY.getValue(), ChannelBizType.CONSUMERLOANHISTORY_LOAN_RECORD.getValue());
        return new UserDataStatusBo(UserDataStatusBo.DataStatus.SUCCESS, id);
    }

    @Override
    public Integer innerCrossValidationMaster(Integer userDataId) {
        //获取用户信息
        UserData userData = userDataService.get(userDataId);
        if(userData == null){
            throw new BusinessException("用户数据信息不存在");
        }
        //TODO 保存原始数据
        Integer id = 1;//consumerLoanHistoryNaiveDomain.sendMasterInfoReq(userData.getName(), userData.getIdCard());
        if (id == null) {
            throw new BusinessException("交叉验证本人信息返回ID为空！");
        }
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.CONSUMERLOANHISTORY.getValue(), ChannelBizType.CROSS_VALIDATION_MASTER.getValue());
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
                    ChannelBizType.APP_COMPLEX.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
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
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.CONSUMERLOANHISTORY.getValue(), ChannelBizType.CROSS_VALIDATION_CONTACT.getValue());
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
                    ChannelBizType.CROSS_VALIDATION_EMERGENCYS.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
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
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.CONSUMERLOANHISTORY.getValue(), ChannelBizType.CROSS_VALIDATION_EMERGENCYS.getValue());
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
//        userDataService.addChannelData(userDataId, bankPortraitId, ChannelType.PSAPP.getValue(), ChannelBizType.BT_BANK_PORTRAIT.getValue());
//        return bankPortraitId;
        return null;
    }

    @Override
    public List<Integer> findAddressCrossValidationVariableId(Integer userDataId) {
        UserDataBo userDataBo = this.get(userDataId);
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
            if("USER_BASICINFO".equalsIgnoreCase(userVariableBo.getVariableType().getValue())){
                variableIdList.add(userVariableBo.getVariableDataId());
                break;
            }
        }
        for (UserVariableBo userVariableBo : userVariableList) {
            if("USER_CONTACTS_INFO".equalsIgnoreCase(userVariableBo.getVariableType().getValue())){
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
                    ChannelBizType.CROSS_VALIDATION_MASTER.getValue().equals(userVariable.getUserVariableDataFrom().getChannelBizType())) {
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

    @Override
    public String oloanLoanApply(Integer userDataId) {
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        List<UserFeatureBo> userFeatureList = userDataBo.getUserFeatureList();
        JSONObject jsonObject = new JSONObject();
        if (userFeatureList != null && userFeatureList.size() > 0) {
            List<Integer> featureDataidList = new ArrayList<>();
            for (UserFeatureBo userFeatureBo : userFeatureList) {
                featureDataidList.add(userFeatureBo.getFeatureDataId());
            }
            List<FeatureDataBo> featureDataBoListList = featureDomain.findFeatureList(featureDataidList);
            //封装特征值结构
            jsonObject = sealFeatureValues(featureDataBoListList);
        }
        LOGGER.info("申请贷款入参：" + jsonObject.toJSONString());
        // 请求决策中心获取决策结果
        ArcBorrowRuleResultBo borrowRuleResultBo = decisionRemoteDomain.getResultByRuleEngine(jsonObject);

        return JSONObject.toJSONString(borrowRuleResultBo);
    }

    @Override
    public Integer fillLoanApplyInfo(Integer userDataId, String originalData) {
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        Integer id = loanHistoryDomain.addCunsumerLoanHistoryData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), JSONObject.toJSONString(originalData));
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.CONSUMERLOANHISTORY.getValue(), ChannelBizType.CONSUMERLOANHISTORY_LOAN_RECORD.getValue());
        return id;
    }

     @Override
    public Integer fillSimInfo(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
         if(userDataBo == null){
             throw new BusinessException("用户数据信息不存在");
         }
        Integer id = moxieSIMDomain.addMoxieSIMData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.MOXIESIMCARD.getValue(), ChannelBizType.MOXIE_SIM_CARD_INFO.getValue());
        return id;
    }

    @Override
    public String getMoxieSNSInfoFromThirdParty(String requestParam) {
        return moxieSNSDomain.sendRequest(requestParam);
    }

    @Override
    public Integer fillSNSInfo(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        Integer id = moxieSNSDomain.addMoxieSNSData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.MOXIESNS.getValue(), ChannelBizType.MOXIE_SNS_INFO.getValue());
        return id;
    }

    @Override
    public Integer fillTDBodyGuardInfo(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        Integer id = tdBodyGuardDomain.addTDBodyGuardData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.TONGDUNCREDITBODYGUARD.getValue(), ChannelBizType.TONGDUN_CREDIT_BODYGUARD_INFO.getValue());
        return id;
    }

    @Override
    public String getTDBodyGuardInfoFromThirdParty(String requestParam) {
        return tdBodyGuardDomain.sendRequest(requestParam);
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

    /**
     * 封装特征集合
     * @param featureDataBoListList 特征集合
     */
    private JSONObject sealFeatureValues(List<FeatureDataBo> featureDataBoListList) {
        JSONObject result = new JSONObject();

        for (FeatureDataBo featureDataBo : featureDataBoListList) {
            List<FeatureDataValueBo> featureDataValueBoList = featureDataBo.getValueList();
            for (FeatureDataValueBo featureDataValueBo : featureDataValueBoList) {
                result.put(featureDataValueBo.getKey(), featureDataValueBo.getValue());
            }
        }
        return result;
    }

    @Override
    public Integer fillCallRecordInfo(Integer userDataId, String originalData) {
        //保存用户基本信息原始数据并返回ID
        UserDataBo userDataBo = this.get(userDataId);
        if(userDataBo == null){
            throw new BusinessException("用户数据信息不存在");
        }
        Integer id = appDataDomain.addAppCallRecordData(userDataBo.getName(), userDataBo.getAadhaarNo(), userDataBo.getMobile(), userDataBo.getEmail(), userDataBo.getDeviceFingerprint(), originalData);
        //关联完整用户基本信息到用户信息中
        userDataService.addChannelData(userDataId, id, ChannelType.PSAPP.getValue(), ChannelBizType.APP_CALLRECORDS.getValue());
        return id;
    }

    @Override
    public AppCallRecordDataBo getAppCallRecordData(Integer dataId) {
        return appDataDomain.getAppCallRecordData(dataId);
    }
}
