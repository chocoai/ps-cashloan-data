package com.adpanshi.cashloan.data.thirdparty.pancard.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.thirdparty.pancard.pojo.PanCardData;
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
public class PanCardService {

    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条盘卡原始数据
     * @return 主键id
     */
    public Integer addAppPanCardData(String panNo, String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        PanCardData panCardData = new PanCardData();
        panCardData.setFid(IdGenerator.getId(CollectionName.PANCARD_DATA));
        panCardData.setName(name);
        panCardData.setMobile(mobile);
        panCardData.setEmail(email);
        panCardData.setAadhaarNo(aadhaarNo);
        panCardData.setOriginalData(originalData);
        panCardData.setDeviceFingerprint(deviceFingerprint);
        panCardData.setPanNo(panNo);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        panCardData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.PANCARD_DATA, panCardData);
        return  panCardData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public PanCardData getPanCardData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<PanCardData> panCardData = mongoUtil.find(CollectionName.PANCARD_DATA, bson, PanCardData.class);
        return panCardData.isEmpty() ? null : panCardData.get(0);
    }
}
