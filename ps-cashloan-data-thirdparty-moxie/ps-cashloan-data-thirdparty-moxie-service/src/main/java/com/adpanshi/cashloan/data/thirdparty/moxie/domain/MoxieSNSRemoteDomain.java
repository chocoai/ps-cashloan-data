package com.adpanshi.cashloan.data.thirdparty.moxie.domain;

import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSNSBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class MoxieSNSRemoteDomain implements MoxieSNSDomain {

    private static Logger logger = LoggerFactory.getLogger(MoxieSNSRemoteDomain.class);

    @Resource
    private MoxieSNSNativeDomain moxieSNSNativeDomain;



    @Override
    public MoxieSNSBo getMetaData(Integer snsDataId){
        return moxieSNSNativeDomain.getMetaData(snsDataId);
    }

    @Override
    public String sendRequest(String requestParam) {
        return moxieSNSNativeDomain.sendRequest(requestParam);
    }

    @Override
    public Integer addMoxieSNSData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return moxieSNSNativeDomain.addMoxieSNSData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

}
