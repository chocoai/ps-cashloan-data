package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppEquipmentData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * APP设备
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class AppEquipmentDataService {

    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条app设备信息原始数据
     * @return 主键id
     */
    public Integer addAppEquipmentData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        AppEquipmentData appEquipmentData = new AppEquipmentData();
        appEquipmentData.setFid(IdGenerator.getId(CollectionName.APP_EQUIPMENT_DATA));
        appEquipmentData.setName(name);
        appEquipmentData.setMobile(mobile);
        appEquipmentData.setEmail(email);
        appEquipmentData.setAadhaarNo(aadhaarNo);
        appEquipmentData.setOriginalData(originalData);
        appEquipmentData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appEquipmentData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.APP_EQUIPMENT_DATA, appEquipmentData);
        return  appEquipmentData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public AppEquipmentData getAppEquipmentData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppEquipmentData> appEquipmentData = mongoUtil.find(CollectionName.APP_EQUIPMENT_DATA, bson, AppEquipmentData.class);
        return appEquipmentData.isEmpty() ? null : appEquipmentData.get(0);
    }
}
