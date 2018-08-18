package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppUserBaseInfoData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * APP用户基本信息
 *  Created by zsw on 2018/6/29 0029.
 */
@Service
public class AppUserBaseInfoDataService {


    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条app用户基本信息原始数据
     * @return 主键id
     */
    public Integer addAppUserBaseInfoData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        AppUserBaseInfoData appUserBaseInfoData = new AppUserBaseInfoData();
        appUserBaseInfoData.setFid(IdGenerator.getId(CollectionName.APP_USERBASEINFO_DATA));
        appUserBaseInfoData.setName(name);
        appUserBaseInfoData.setMobile(mobile);
        appUserBaseInfoData.setEmail(email);
        appUserBaseInfoData.setAadhaarNo(aadhaarNo);
        appUserBaseInfoData.setOriginalData(originalData);
        appUserBaseInfoData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appUserBaseInfoData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.APP_USERBASEINFO_DATA, appUserBaseInfoData);
        return  appUserBaseInfoData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public AppUserBaseInfoData getAppUserBaseInfoData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppUserBaseInfoData> appUserBaseInfoData = mongoUtil.find(CollectionName.APP_USERBASEINFO_DATA, bson, AppUserBaseInfoData.class);
        return appUserBaseInfoData.isEmpty() ? null : appUserBaseInfoData.get(0);
    }
}

