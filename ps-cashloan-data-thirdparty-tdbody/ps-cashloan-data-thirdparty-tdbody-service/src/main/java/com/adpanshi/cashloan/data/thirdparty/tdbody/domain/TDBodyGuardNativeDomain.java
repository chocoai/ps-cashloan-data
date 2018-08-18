package com.adpanshi.cashloan.data.thirdparty.tdbody.domain;

import com.adpanshi.cashloan.data.thirdparty.tdbody.bo.TDBodyGuardBo;
import com.adpanshi.cashloan.data.thirdparty.tdbody.service.TDBodyGdService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service("tdBodyGuardDomain")
public class TDBodyGuardNativeDomain implements TDBodyGuardDomain {

    @Resource
    private TDBodyGdService tdBodyGdService;

    @Override
    public TDBodyGuardBo getTDBodyGuardData(Integer simDataId) {
        return tdBodyGdService.get(simDataId);
    }

    @Override
    public Integer addTDBodyGuardData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return tdBodyGdService.add(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    @Override
    public String sendRequest(String requestParam){
        return tdBodyGdService.sendRequest(requestParam);
    }

}
