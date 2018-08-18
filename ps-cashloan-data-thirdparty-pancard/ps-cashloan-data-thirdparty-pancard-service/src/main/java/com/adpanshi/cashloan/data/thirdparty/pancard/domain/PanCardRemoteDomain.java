package com.adpanshi.cashloan.data.thirdparty.pancard.domain;

import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;
import com.adpanshi.cashloan.data.thirdparty.pancard.service.PanCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zsw on 2018/8/2 0002.
 * @author 8452
 */
@Service
public class PanCardRemoteDomain implements PanCardDomain {
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(PanCardService.class);
    @Resource
    private PanCardNativeDomain panCardDomain;


    @Override
    public PanCardDataBo getPanInfo(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String requestParams) {
        return panCardDomain.getPanInfo(name, aadhaarNo, mobile, email, deviceFingerprint, requestParams);
    }

    @Override
    public PanCardDataBo getPanInfoForOldData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String oldData) {
        return panCardDomain.getPanInfoForOldData(name, aadhaarNo, mobile, email, deviceFingerprint, oldData);
    }
}
