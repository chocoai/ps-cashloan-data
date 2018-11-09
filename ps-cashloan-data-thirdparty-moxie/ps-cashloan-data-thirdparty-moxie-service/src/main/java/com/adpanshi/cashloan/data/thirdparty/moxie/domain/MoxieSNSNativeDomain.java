package com.adpanshi.cashloan.data.thirdparty.moxie.domain;

import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSNSBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.service.MoxieSNSService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service("moxieSNSDomain")
public class MoxieSNSNativeDomain implements MoxieSNSDomain {

    @Resource
    private MoxieSNSService moxieSNSService;

    @Override
    public MoxieSNSBo getMoxieSNSMetaData(Integer dataId) {
        return moxieSNSService.get(dataId);
    }

    @Override
    public String sendRequest(String requestParam) {
        return moxieSNSService.sendRequest(requestParam);
    }

    @Override
    public Integer addMoxieSNSData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return moxieSNSService.add(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }
}
