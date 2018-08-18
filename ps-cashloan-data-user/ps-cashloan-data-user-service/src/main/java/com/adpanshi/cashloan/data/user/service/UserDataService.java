package com.adpanshi.cashloan.data.user.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.user.pojo.*;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class UserDataService {

    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条数据
     */
    public UserData add(UserBaseData userBaseData){
        UserData userData = new UserData();
        userData.setFid(IdGenerator.getId(CollectionName.USER_DATA));
        userData.setCountryType(userBaseData.getCountryType());
        userData.setProductType(userBaseData.getProductType());
        userData.setAadhaarNo(userBaseData.getAadhaarNo());
        userData.setMobile(userBaseData.getMobile());
        userData.setEmail(userBaseData.getEmail());
        userData.setName(userBaseData.getName());
        userData.setDeviceFingerprint(userBaseData.getDeviceFingerprint());
        userData.setUserMetaDataList(new ArrayList<UserMetaData>());
        userData.setUserVariableList(new ArrayList<UserVariable>());
        userData.setUserFeatureList(new ArrayList<UserFeature>());
        userData.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        userData.setLastModifyTime(userData.getCreateTime());
        mongoUtil.insert(CollectionName.USER_DATA, userData);
        return userData;
    }

    /**
     * 获取一条数据
     */
    public UserData get(Integer fid) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", fid)});
        List<UserData> userDataList = mongoUtil.find(CollectionName.USER_DATA, bson, UserData.class);
        if (userDataList.size() == 0) {
            return null;
        }
        if (userDataList.size() > 1) {
            throw new BusinessException("用户数据异常,id:" + fid);
        }
        return userDataList.get(0);
    }

    /**
     * 添加一条原始数据
     */
    public void addChannelData(Integer userDataId, Integer channelDataId, Integer channelType, Integer channelBizType) {
        this.addChannelData2UserData(userDataId, channelDataId, channelType, channelBizType, null);
    }

    /**
     * 添加一条原始数据
     */
    public void addChannelData2UserData(Integer userDataId, Integer channelDataId, Integer channelType, Integer channelBizType, UserData userDataParam) {
        UserData userData;
        if(userDataParam == null){
            //获取用户数据
            userData = get(userDataId);

        }else{
            userData = userDataParam;
        }
        if (userData == null) {
            throw new BusinessException("用户数据信息为空,查询参数：" + userDataId);
        }
        //获取用户原始数据
        List<UserMetaData> userMetaDataList = userData.getUserMetaDataList();
        if (userMetaDataList == null) {
            userMetaDataList = new ArrayList<>();
        }
        UserMetaData userMetaData = new UserMetaData();
        userMetaData.setChannelDataId(channelDataId);
        userMetaData.setChannelType(channelType);
        userMetaData.setChannelBizType(channelBizType);
        userMetaData.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        userMetaDataList.add(userMetaData);
        this.modify(userData);
    }

    /**
     * 更新一条数据
     */
    public void modify(UserData userData){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", userData.getFid())});
        mongoUtil.updateData(CollectionName.USER_DATA, bson, Document.parse(JSON.toJSONString(userData, SerializerFeature.DisableCircularReferenceDetect)));
    }

}





