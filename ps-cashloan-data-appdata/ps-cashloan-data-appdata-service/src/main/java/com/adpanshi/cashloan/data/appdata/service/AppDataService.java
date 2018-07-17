package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *  Created by zsw on 2018/6/29 0029.
 */
@Service("appDataService")
public class AppDataService {


    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条appdata原始数据
     * @return 主键id
     */
    public Integer addAppData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        AppData appData = new AppData();
        appData.setFid(IdGenerator.getId(CollectionName.APP_DATA));
        appData.setName(name);
        appData.setMobile(mobile);
        appData.setEmail(email);
        appData.setAadhaarNo(aadhaarNo);
        appData.setOriginalData(originalData);
        appData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.APP_DATA,appData);
        return  appData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public AppData getAppData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppData> appData = mongoUtil.find(CollectionName.APP_DATA, bson, AppData.class);
        return appData.isEmpty() ? null : appData.get(0);
    }
    }

