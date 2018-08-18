package com.adpanshi.cashloan.data.thirdparty.equifax.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.thirdparty.equifax.pojo.EquifaxReportData;
import com.adpanshi.cashloan.data.thirdparty.equifax.pojo.EquifaxReportDataLog;
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
public class EquifaxCreditReportService {
    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条信用报告原始数据
     * @param panNo
     * @param name
     * @param aadhaarNo
     * @param mobile
     * @param email
     * @param deviceFingerprint
     * @param originalData
     * @return
     */
    public Integer addEquifaxCreditReporData(String panNo, String firstName, String lastName, String name, String aadhaarNo, String mobile, String email, String deviceFingerprint,String uuid , String originalData){

        EquifaxReportData equifaxReportData = new EquifaxReportData();
        equifaxReportData.setFid(IdGenerator.getId(CollectionName.EQUIFAX_CREDITREPORT_DATA));
        equifaxReportData.setName(name);
        equifaxReportData.setMobile(mobile);
        equifaxReportData.setFirstName(firstName);
        equifaxReportData.setLastName(lastName);
        equifaxReportData.setEmail(email);
        equifaxReportData.setAadhaarNo(aadhaarNo);
        equifaxReportData.setOriginalData(originalData);
        equifaxReportData.setDeviceFingerprint(deviceFingerprint);
        equifaxReportData.setPanNo(panNo);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        equifaxReportData.setCreateTime(currentTime);
        equifaxReportData.setUuid(uuid);

        mongoUtil.insert(CollectionName.EQUIFAX_CREDITREPORT_DATA, equifaxReportData);
        return  equifaxReportData.getFid();

    }

    /**
     * 添加日志记录
     * @param mobile
     * @param email
     * @param status
     * @param uuid
     * @param parameterData
     * @param originalData
     * @return
     */
    public Integer addEquifaxCreditReporDataLog(String mobile, String email, String status, String uuid, String parameterData, String originalData){
        EquifaxReportDataLog equifaxReportDataLog = new EquifaxReportDataLog();
        equifaxReportDataLog.setFid(IdGenerator.getId(CollectionName.EQUIFAX_CREDITREPORT_DATA_LOG));
        equifaxReportDataLog.setMobile(mobile);
        equifaxReportDataLog.setEmail(email);
        equifaxReportDataLog.setStatus(status);
        equifaxReportDataLog.setParameterData(parameterData);
        equifaxReportDataLog.setUuid(uuid);
        equifaxReportDataLog.setOriginalData(originalData);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        equifaxReportDataLog.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.EQUIFAX_CREDITREPORT_DATA_LOG, equifaxReportDataLog);
        return  equifaxReportDataLog.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public EquifaxReportData getEquifaxReportData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<EquifaxReportData> panCardData = mongoUtil.find(CollectionName.EQUIFAX_CREDITREPORT_DATA, bson, EquifaxReportData.class);
        return panCardData.isEmpty() ? null : panCardData.get(0);
    }
}
