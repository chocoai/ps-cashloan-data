package com.adpanshi.cashloan.data.appdata.domain;


import com.adpanshi.cashloan.data.appdata.bo.AppDataBo;

/**
 * app数据接口相关
 *  Created by zsw on 2018/6/29 0029.
 */
public interface AppDataDomain {

    /**
     * 添加一条数据
     */
    Integer addAppData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData);

    /**
     * 根据ID去app原始数据
     * @param id app数据id
     */
    AppDataBo getAppData(Integer id);
}
