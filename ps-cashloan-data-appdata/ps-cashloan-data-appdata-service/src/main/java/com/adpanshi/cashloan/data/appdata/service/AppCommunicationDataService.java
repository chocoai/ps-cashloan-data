package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppCommunicationData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * APP通讯录
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class AppCommunicationDataService {

    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条app通讯录原始数据
     * @return 主键id
     */
    public Integer addAppCommunicationData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        AppCommunicationData appCommunicationData = new AppCommunicationData();
        appCommunicationData.setFid(IdGenerator.getId(CollectionName.APP_COMMUNICATION_DATA));
        appCommunicationData.setName(name);
        appCommunicationData.setMobile(mobile);
        appCommunicationData.setEmail(email);
        appCommunicationData.setAadhaarNo(aadhaarNo);
        appCommunicationData.setOriginalData(originalData);
        appCommunicationData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appCommunicationData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.APP_COMMUNICATION_DATA, appCommunicationData);
        return  appCommunicationData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public AppCommunicationData getAppCommunicationData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppCommunicationData> appCommunicationData = mongoUtil.find(CollectionName.APP_COMMUNICATION_DATA, bson, AppCommunicationData.class);
        return appCommunicationData.isEmpty() ? null : appCommunicationData.get(0);
    }
}
