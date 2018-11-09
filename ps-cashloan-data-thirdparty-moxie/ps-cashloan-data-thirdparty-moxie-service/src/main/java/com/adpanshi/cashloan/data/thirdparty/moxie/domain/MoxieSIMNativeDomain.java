package com.adpanshi.cashloan.data.thirdparty.moxie.domain;

import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.service.MoxieSIMService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service("moxieSIMDomain")
public class MoxieSIMNativeDomain implements MoxieSIMDomain {

    @Resource
    private MoxieSIMService moxieSIMService;

    @Override
    public MoxieSIMBo getMoxieSIMMetaData(Integer dataId) {
        return moxieSIMService.get(dataId);
    }

    @Override
    public Integer addMoxieSIMData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return moxieSIMService.add(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }


}
