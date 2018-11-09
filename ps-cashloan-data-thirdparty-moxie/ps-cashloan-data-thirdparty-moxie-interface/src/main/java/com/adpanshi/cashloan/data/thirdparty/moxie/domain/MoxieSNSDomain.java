package com.adpanshi.cashloan.data.thirdparty.moxie.domain;

import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSNSBo;

/**
 * Created by zsw on 2018/8/2 0002.
 */
public interface MoxieSNSDomain {


    /**
     * 获取用户sns原始数据By主键
     * @param dataId
     * @return
     */
    MoxieSNSBo getMoxieSNSMetaData(Integer dataId);

    /**
     * 获取磨盒社交信息
     * @param requestParam  请求参数
     * @return  返回结果
     */
    String sendRequest(String requestParam);

    /**
     *添加磨盒SNS社交原始数据
     */
    Integer addMoxieSNSData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

}
