package com.adpanshi.cashloan.data.appdata.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.pojo.AppPanCardData;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: hujin3
 * @description: pan卡信息
 * @author: Mr.Wange
 * @create: 2018-08-03 09:33
 **/
@Service
public class AppPanCardDataService {
    @Resource
    private MongoUtil mongoUtil;

    public Integer addPanCardData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        AppPanCardData appPanCardData = new AppPanCardData();
        appPanCardData.setFid(IdGenerator.getId(CollectionName.APP_EQUIPMENT_DATA));
        appPanCardData.setName(name);
        appPanCardData.setMobile(mobile);
        appPanCardData.setEmail(email);
        appPanCardData.setAadhaarNo(aadhaarNo);
        appPanCardData.setOriginalData(originalData);
        appPanCardData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        appPanCardData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.PANCARD_DATA, appPanCardData);
        return  appPanCardData.getFid();
    }

    public AppPanCardData getPanCardData(Integer dataId) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<AppPanCardData> appPanCardData = mongoUtil.find(CollectionName.APP_USERBASEINFO_DATA, bson, AppPanCardData.class);
        return appPanCardData.isEmpty() ? null : appPanCardData.get(0);
    }
}
