package com.adpanshi.cashloan.data.appdata.domain;


import com.adpanshi.cashloan.data.appdata.bo.*;

/**
 * app数据接口相关
 *  Created by zsw on 2018/6/29 0029.
 */
public interface AppDataDomain {

    /**
     * 添加app用户基本信息原始数据
     * @param name              用户全称
     * @param aadhaarNo         aadhaarNo编号
     * @param mobile            手机号
     * @param email             邮箱
     * @param deviceFingerprint 设备指纹
     * @param originalData      原始数据
     * @return                  原始数据ID
     */
    Integer addAppUserBaseInfoData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据ID获取app用户基本信息原始数据
     * @param id app数据id
     */
    AppUserBaseInfoDataBo getAppUserBaseInfoData(Integer id);

    /**
     * 添加app手机应用原始数据
     * @param name              用户全称
     * @param aadhaarNo         aadhaarNo编号
     * @param mobile            手机号
     * @param email             邮箱
     * @param deviceFingerprint 设备指纹
     * @param originalData      原始数据
     * @return                  原始数据ID
     */
    Integer addAppApplicationData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据ID获取app手机应用原始数据
     * @param id app数据id
     */
    AppApplicationDataBo getAppApplicationData(Integer id);

    /**
     * 添加app通讯录原始数据
     * @param name              用户全称
     * @param aadhaarNo         aadhaarNo编号
     * @param mobile            手机号
     * @param email             邮箱
     * @param deviceFingerprint 设备指纹
     * @param originalData      原始数据
     * @return                  原始数据ID
     */
    Integer addAppCommunicationData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);
    /**
     * 根据ID获取app紧急联系人原始数据
     * @param dataId app数据id
     */
    AppEmergencyDataBo getAppEmergencyData(Integer dataId);

    /**
     * 添加app紧急联系人原始数据
     * @param name              用户全称
     * @param aadhaarNo         aadhaarNo编号
     * @param mobile            手机号
     * @param email             邮箱
     * @param deviceFingerprint 设备指纹
     * @param originalData      原始数据
     * @return                  原始数据ID
     */
    Integer addAppEmergencyData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据ID获取app通讯录原始数据
     * @param id app数据id
     */
    AppCommunicationDataBo getAppCommunicationData(Integer id);

    /**
     * 添加app设备信息原始数据
     * @param name              用户全称
     * @param aadhaarNo         aadhaarNo编号
     * @param mobile            手机号
     * @param email             邮箱
     * @param deviceFingerprint 设备指纹
     * @param originalData      原始数据
     * @return                  原始数据ID
     */
    Integer addAppEquipmentData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据ID获取app设备信息原始数据
     * @param id app数据id
     */
    AppEquipmentDataBo getAppEquipmentData(Integer id);

    /**
     * 添加pan卡信息原始数据
     * @param name              用户全称
     * @param aadhaarNo         aadhaarNo编号
     * @param mobile            手机号
     * @param email             邮箱
     * @param deviceFingerprint 设备指纹
     * @param originalData      原始数据
     * @return                  原始数据ID
     */
    Integer addPanCardData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据ID获取pan卡信息原始数据
     * @param dataId app数据id
     */
    AppPanCardDataBo getPanCardData(Integer dataId);

    /**
     * 添加通话记录信息原始数据
     * @param name              用户全称
     * @param aadhaarNo         aadhaarNo编号
     * @param mobile            手机号
     * @param email             邮箱
     * @param deviceFingerprint 设备指纹
     * @param originalData      原始数据
     * @return                  原始数据ID
     */
    Integer addAppCallRecordData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据Id获取通话记录信息原始数据
     * @param dataId
     * @return
     */
    AppCallRecordDataBo getAppCallRecordData(Integer dataId);




}
