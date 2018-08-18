package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppCallRecordData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.logging.Filter;

/**
 * @program: hujin3
 * @description: 通讯录信息
 * @author: cxl
 * @create: 2018-08-11 11:00
 */
@Service
public class AppCallRecordDataService {
    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加通话记录原始数据
     * @param name
     * @param aadhaarNo
     * @param mobile
     * @param email
     * @param deviceFingerprint
     * @param originalData
     * @return
     */
    public Integer addAppCallRecordData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){
        AppCallRecordData  appCallRecordData =new AppCallRecordData();
        appCallRecordData.setFid(IdGenerator.getId(CollectionName.APP_CALLRECORD_DATA));
        appCallRecordData.setName(name);
        appCallRecordData.setMobile(mobile);
        appCallRecordData.setEmail(email);
        appCallRecordData.setAadhaarNo(aadhaarNo);
        appCallRecordData.setOriginalData(originalData);
        appCallRecordData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appCallRecordData.setCreateTime(currentTime);
        mongoUtil.insert(CollectionName.APP_CALLRECORD_DATA,appCallRecordData);

        return appCallRecordData.getFid();
    }

    /**
     * 根据id获取一条数据
     * @param dataId
     * @return
     */
    public  AppCallRecordData getAppCallRecordData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppCallRecordData> appCallRecordData = mongoUtil.find(CollectionName.APP_CALLRECORD_DATA, bson, AppCallRecordData.class);
        return appCallRecordData.isEmpty()?null:appCallRecordData.get(0);

    }
}
