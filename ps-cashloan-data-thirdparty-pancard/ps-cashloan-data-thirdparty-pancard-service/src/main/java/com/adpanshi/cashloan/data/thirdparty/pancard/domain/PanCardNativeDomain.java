package com.adpanshi.cashloan.data.thirdparty.pancard.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.HttpClientUtil;
import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;
import com.adpanshi.cashloan.data.thirdparty.pancard.constant.PanMessageConstant;
import com.adpanshi.cashloan.data.thirdparty.pancard.exception.PanCardException;
import com.adpanshi.cashloan.data.thirdparty.pancard.pojo.PanCardData;
import com.adpanshi.cashloan.data.thirdparty.pancard.service.PanCardService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsw on 2018/8/2 0002.
 * @author 8452
 */
@Service("panCardDomain")
public class PanCardNativeDomain implements PanCardDomain {
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(PanCardService.class);

    @Resource
    private PanCardService panCardService;

    @Value("${pan.url}")
    private String panCardUrl;

    @Value("${pan.key}")
    private String panCardKey;

    @Override
    public PanCardDataBo getPanInfoForOldData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String oldData){
        if(StringUtil.isBlank(oldData)) {
            throw new PanCardException(PanMessageConstant.OLD_PARAMS_ERROR);
        }
        JSONObject paramsJson = JSONObject.parseObject(oldData);
        if (!paramsJson.containsKey("panNo")) {
            throw new PanCardException(PanMessageConstant.REQUEST_PARAMS_ERROR);
        }
        String panNo = paramsJson.getString("panNo");
        //保存数据
        Integer dataId = panCardService.addAppPanCardData(panNo, name, aadhaarNo, mobile, email, deviceFingerprint, oldData);
        if (dataId == null) {
            throw new PanCardException(PanMessageConstant.SAVE_PANCARD_META_ERROR);
        }
        PanCardData panCardData = panCardService.getPanCardData(dataId);
        return BeanUtil.convert(panCardData, PanCardDataBo.class);
    }

    @Override
    public PanCardDataBo getPanInfo(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String requestParams) {
        try {
            JSONObject paramsJson = JSONObject.parseObject(requestParams);
            if (!paramsJson.containsKey("panNo")) {
                throw new PanCardException(PanMessageConstant.REQUEST_PARAMS_ERROR);
            }
            String panNo = paramsJson.getString("panNo");
            //请求第三方接口
            String responseResult = sendRequest(panNo);
//            String responseResult = "{'firstName':'aaa','lastName':'bbb',}";// TODO 测试用
            if(StringUtil.isBlank(responseResult)) {
                throw new PanCardException(PanMessageConstant.RESPONSE_RESULT_ERROR);
            }
            //保存原始数据
            Integer dataId = panCardService.addAppPanCardData(panNo, name, aadhaarNo, mobile, email, deviceFingerprint, responseResult);
            if (dataId == null) {
                throw new PanCardException(PanMessageConstant.SAVE_PANCARD_META_ERROR);
            }
            PanCardData panCardData = panCardService.getPanCardData(dataId);
            return BeanUtil.convert(panCardData, PanCardDataBo.class);
        } catch (PanCardException e) {
            logger.error("请求获取盘卡信息异常", e.getMessage());
            return null;
        }
    }

    private String sendRequest(String panNo) {
        Map sendMap = new HashMap<>(16);
        sendMap.put("key",panCardKey);
        sendMap.put("pan_no",panNo);
        //发送pan卡接口
        return HttpClientUtil.sendPostRequest(panCardUrl,sendMap,null,null);
    }
}
