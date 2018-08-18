package com.adpanshi.cashloan.data.appdata.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.appdata.bo.*;
import com.adpanshi.cashloan.data.appdata.exception.AppDataException;
import com.adpanshi.cashloan.data.appdata.pojo.*;
import com.adpanshi.cashloan.data.appdata.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * app存取数据接口
 *  Created by zsw on 2018/6/29 0029.
 */
@Service("appDataDomain")
public class AppDataNativeDomain implements AppDataDomain {
    @Resource
    private AppUserBaseInfoDataService appUserBaseInfoDataService;

    @Resource
    private AppApplicationDataService appApplicationDataService;

    @Resource
    private AppCommunicationDataService appCommunicationDataService;

    @Resource
    private AppEquipmentDataService appEquipmentDataService;

    @Resource
    private AppPanCardDataService appPanCardDataService;
    @Resource
    private AppEmergencyDataService appEmergencyDataService;
    @Resource
    private AppCallRecordDataService appCallRecordDataService;

    @Override
    public Integer addAppUserBaseInfoData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return appUserBaseInfoDataService.addAppUserBaseInfoData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    /**
     * 根据ID取一条原始数据
     * @param dataId app数据id
     */
    @Override
    public AppUserBaseInfoDataBo getAppUserBaseInfoData(Integer dataId) {
        AppUserBaseInfoData appUserBaseInfoData = appUserBaseInfoDataService.getAppUserBaseInfoData(dataId);
        if (appUserBaseInfoData == null) {
            throw new AppDataException("dataId="+dataId+"获取app用户基本信息数据为空");
        }
        return BeanUtil.convert(appUserBaseInfoData, AppUserBaseInfoDataBo.class);
    }

    @Override
    public Integer addAppApplicationData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return appApplicationDataService.addAppApplicationData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    @Override
    public AppApplicationDataBo getAppApplicationData(Integer dataId) {
        AppApplicationData appApplicationData = appApplicationDataService.getAppApplicationData(dataId);
        if (appApplicationData == null) {
            throw new AppDataException("dataId="+dataId+"获取app手机应用数据为空");
        }
        return BeanUtil.convert(appApplicationData, AppApplicationDataBo.class);
    }

    @Override
    public Integer addAppCommunicationData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return appCommunicationDataService.addAppCommunicationData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }
    @Override
    public AppEmergencyDataBo getAppEmergencyData(Integer dataId) {
        AppEmergencyData appEmergencyData = appEmergencyDataService.getAppEmergencyData(dataId);
        if (appEmergencyData == null) {
            throw new AppDataException("dataId="+dataId+"获取app紧急联系人数据为空");
        }
        return BeanUtil.convert(appEmergencyData, AppEmergencyDataBo.class);
    }

    @Override
    public Integer addAppEmergencyData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return appEmergencyDataService.addAppEmergencyData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    @Override
    public AppCommunicationDataBo getAppCommunicationData(Integer dataId) {
        AppCommunicationData appCommunicationData = appCommunicationDataService.getAppCommunicationData(dataId);
        if (appCommunicationData == null) {
            throw new AppDataException("dataId="+dataId+"获取app通讯录数据为空");
        }
        return BeanUtil.convert(appCommunicationData, AppCommunicationDataBo.class);
    }

    @Override
    public Integer addAppEquipmentData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return appEquipmentDataService.addAppEquipmentData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    @Override
    public AppEquipmentDataBo getAppEquipmentData(Integer dataId) {
        AppEquipmentData appEquipmentData = appEquipmentDataService.getAppEquipmentData(dataId);
        if (appEquipmentData == null) {
            throw new AppDataException("dataId="+dataId+"获取app设备信息数据为空");
        }
        return BeanUtil.convert(appEquipmentData, AppEquipmentDataBo.class);
    }

    /**
     * 添加pan卡信息原始数据
     *
     * @param name
     * @param aadhaarNo
     * @param mobile
     * @param email
     * @param deviceFingerprint
     * @param originalData
     */
    @Override
    public Integer addPanCardData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return appPanCardDataService.addPanCardData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    /**
     * 根据ID获取pan卡信息原始数据
     *
     * @param dataId app数据id
     */
    @Override
    public AppPanCardDataBo getPanCardData(Integer dataId) {
        AppPanCardData appPanCardData = appPanCardDataService.getPanCardData(dataId);
        if (appPanCardData == null){
            throw new AppDataException("dataId="+dataId+"获取pan卡信息数据为空");
        }
        return BeanUtil.convert(appPanCardData, AppPanCardDataBo.class);
    }

    @Override
    public Integer addAppCallRecordData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {

        return appCallRecordDataService.addAppCallRecordData(name,aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    @Override
    public AppCallRecordDataBo getAppCallRecordData(Integer dataId) {
        AppCallRecordData appCallRecordData = appCallRecordDataService.getAppCallRecordData(dataId);
        if (appCallRecordData ==null){
            throw  new AppDataException("dataId="+dataId+"获取app通话记录信息数据为空");
        }
        return BeanUtil.convert(appCallRecordData,AppCallRecordDataBo.class);
    }
}
