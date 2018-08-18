package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppEmergencyData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author fish_coder
 * @Title: AppEmergencyDataService
 * @ProjectName fenqidai-dubbo
 * @Description: TODO
 * @date 2018/8/1016:33
 */
@Service
public class AppEmergencyDataService {
    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条app紧急联系人原始数据
     * @return 主键id
     */
    public Integer addAppEmergencyData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        AppEmergencyData appEmergencyData = new AppEmergencyData();
        appEmergencyData.setFid(IdGenerator.getId(CollectionName.APP_EMERGENCY_DATA));
        appEmergencyData.setName(name);
        appEmergencyData.setMobile(mobile);
        appEmergencyData.setEmail(email);
        appEmergencyData.setAadhaarNo(aadhaarNo);
        appEmergencyData.setOriginalData(originalData);
        appEmergencyData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appEmergencyData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.APP_EMERGENCY_DATA, appEmergencyData);
        return  appEmergencyData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public AppEmergencyData getAppEmergencyData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppEmergencyData> appEmergencyData = mongoUtil.find(CollectionName.APP_EMERGENCY_DATA, bson, AppEmergencyData.class);
        return appEmergencyData.isEmpty() ? null : appEmergencyData.get(0);
    }
}
