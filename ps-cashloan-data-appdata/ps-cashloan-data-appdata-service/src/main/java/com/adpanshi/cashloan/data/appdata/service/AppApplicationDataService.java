package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppApplicationData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class AppApplicationDataService {

    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条app应用列表原始数据
     * @return 主键id
     */
    public Integer addAppApplicationData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        AppApplicationData appApplicationData = new AppApplicationData();
        appApplicationData.setFid(IdGenerator.getId(CollectionName.APP_APPLICATION_DATA));
        appApplicationData.setName(name);
        appApplicationData.setMobile(mobile);
        appApplicationData.setEmail(email);
        appApplicationData.setAadhaarNo(aadhaarNo);
        appApplicationData.setOriginalData(originalData);
        appApplicationData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appApplicationData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.APP_APPLICATION_DATA, appApplicationData);
        return  appApplicationData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public AppApplicationData getAppApplicationData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppApplicationData> appApplicationData = mongoUtil.find(CollectionName.APP_APPLICATION_DATA, bson, AppApplicationData.class);
        return appApplicationData.isEmpty() ? null : appApplicationData.get(0);
    }
}
