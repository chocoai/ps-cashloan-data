package com.adpanshi.cashloan.data.thirdparty.moxie.domain;

import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;

/**
 * Created by zsw on 2018/8/2 0002.
 */
public interface MoxieSIMDomain {


    /**
     * 获取用户SIM原始数据By主键
     * @param simDataId
     * @return
     */
    MoxieSIMBo getMoxieSIMData(Integer simDataId);

    /**
     *添加磨盒SIM卡原始数据
     */
    Integer addMoxieSIMData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);



}
