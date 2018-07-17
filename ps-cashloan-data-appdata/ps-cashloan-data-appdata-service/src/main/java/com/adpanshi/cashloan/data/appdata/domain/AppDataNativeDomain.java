package com.adpanshi.cashloan.data.appdata.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.bo.AppDataBo;
import com.adpanshi.cashloan.data.appdata.exception.AppDataException;
import com.adpanshi.cashloan.data.appdata.pojo.AppData;
import com.adpanshi.cashloan.data.appdata.service.AppDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * app存取数据接口
 *  Created by zsw on 2018/6/29 0029.
 */
@Service
public class AppDataNativeDomain implements AppDataDomain {
    @Resource
    private AppDataService appDataService;

    /**
     * 根据ID取一条原始数据
     * @param dataId app数据id
     */
    @Override
    public AppDataBo getAppData(Integer dataId) {
        AppData appData=appDataService.getAppData(dataId);
        if (appData == null) {
            throw new AppDataException("dataId="+dataId+"获取appData数据为空");
        }
        return BeanUtil.convert(appData, AppDataBo.class);
    }

    /**
     * 添加一条app原始数据
     */
    @Override
    public Integer addAppData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
       return appDataService.addAppData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }


}
