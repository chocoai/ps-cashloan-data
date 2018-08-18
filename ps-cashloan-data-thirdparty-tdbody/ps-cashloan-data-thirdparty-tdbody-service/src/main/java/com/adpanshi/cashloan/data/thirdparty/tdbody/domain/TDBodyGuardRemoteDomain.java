package com.adpanshi.cashloan.data.thirdparty.tdbody.domain;

import com.adpanshi.cashloan.data.thirdparty.tdbody.bo.TDBodyGuardBo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class TDBodyGuardRemoteDomain implements TDBodyGuardDomain {

    @Resource
    private TDBodyGuardNativeDomain tdBodyGuardNativeDomain;


    @Override
    public TDBodyGuardBo getTDBodyGuardData(Integer simDataId){
        return tdBodyGuardNativeDomain.getTDBodyGuardData(simDataId);
    }

    @Override
    public Integer addTDBodyGuardData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return tdBodyGuardNativeDomain.addTDBodyGuardData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    @Override
    public String sendRequest(String requestParam) {
        return tdBodyGuardNativeDomain.sendRequest(requestParam);
    }


}
