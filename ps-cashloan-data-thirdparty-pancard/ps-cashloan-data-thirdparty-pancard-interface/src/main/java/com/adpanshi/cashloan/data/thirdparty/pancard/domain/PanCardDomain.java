package com.adpanshi.cashloan.data.thirdparty.pancard.domain;

import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;

import java.util.Map;

/**
 * @program: hujin3
 * @description: pan卡接口查询
 * @author: Mr.Wange
 * @create: 2018-08-01 15:49
 **/
public interface PanCardDomain {

    /**
     * 获取盘卡信息
     * @param name      用户名全称
     * @param aadhaarNo aadhaarNo
     * @param mobile    手机号
     * @param email     邮箱
     * @param deviceFingerprint 设备号
     * @param requestParams     盘卡号
     * @return  原始数据ID
     */
    PanCardDataBo getPanInfo(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String requestParams);

    /**
     * 保存历史已有数据 不走第三方
     * @param name 用户名全称
     * @param aadhaarNo aadhaarNo
     * @param mobile 手机号
     * @param email  邮箱
     * @param deviceFingerprint 设备号
     * @param oldData 历史数据
     * @return
     */
    PanCardDataBo getPanInfoForOldData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String oldData);


}
