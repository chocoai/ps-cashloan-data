package com.adpanshi.cashloan.data.thirdparty.tdbody.domain;

import com.adpanshi.cashloan.data.thirdparty.tdbody.bo.TDBodyGuardBo;

/**
 * Created by zsw on 2018/8/2 0002.
 */
public interface TDBodyGuardDomain {

    /**
     * 获取同盾解析原始数据By主键
     * @param tdBodyGuardDataId
     * @return
     */
    TDBodyGuardBo getTDBodyGuardData(Integer tdBodyGuardDataId);

    /**
     *添加原始数据
     */
    Integer addTDBodyGuardData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 发送同盾请求获取数据
     * @param requestParam
     * @return
     */
    String sendRequest(String requestParam);

}
