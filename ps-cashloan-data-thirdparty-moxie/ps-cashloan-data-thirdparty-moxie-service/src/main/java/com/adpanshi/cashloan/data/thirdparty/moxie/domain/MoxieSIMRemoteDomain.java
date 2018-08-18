package com.adpanshi.cashloan.data.thirdparty.moxie.domain;

import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class MoxieSIMRemoteDomain implements MoxieSIMDomain {

    @Resource
    private MoxieSIMNativeDomain moxieSIMNativeDomain;



    @Override
    public MoxieSIMBo getMoxieSIMData(Integer simDataId){
        return moxieSIMNativeDomain.getMoxieSIMData(simDataId);
    }

    @Override
    public Integer addMoxieSIMData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return moxieSIMNativeDomain.addMoxieSIMData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }


}
