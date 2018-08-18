package com.adpanshi.cashloan.data.thirdparty.moxie.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSNSBo;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tool.util.HttpUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class MoxieSNSService {

    private final static Logger logger = LoggerFactory.getLogger(MoxieSNSService.class);

    @Autowired
    private MongoUtil mongoUtil;

    /**
     * 获取一条数据
     */
    public MoxieSNSBo get(Integer fid) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", fid)});
        List<MoxieSNSBo> moxieSNSBoList = mongoUtil.find(CollectionName.MOXIESNS_DATA, bson, MoxieSNSBo.class);
        if (moxieSNSBoList.size() == 0) {
            return null;
        }
        if (moxieSNSBoList.size() > 1) {
            throw new BusinessException("获取魔盒SNS数据异常,id:" + fid);
        }
        return moxieSNSBoList.get(0);
    }

    public String sendRequest(String requestParam) {
        String result = null;
        try{
            //解析report信息
            Map<String,String> dataMap = new HashMap<>(16);
            Map<String,Object> reportMap = parseStringToMap(requestParam);
            //获取请求的URL地址
            String url = String.valueOf(reportMap.get("url"));
            dataMap.put("partner_code",String.valueOf(reportMap.get("partner_code")));
            dataMap.put("partner_key",String.valueOf(reportMap.get("partner_key")));
            dataMap.put("task_id",String.valueOf(reportMap.get("task_id")));
            int timeOut = Integer.valueOf(String.valueOf(reportMap.get("moxie_time_out")));
            //向第三方发起请求,设置timeout时间
            result = HttpUtil.postClient(url,dataMap,"UTF-8",timeOut);
        }catch(Exception e) {
            throw new RuntimeException("魔蝎社交查询异常, requestParam = " + requestParam + " , message = " + e.getMessage());
        }
        return result;
    }
    /**
     * 添加一条数据
     */
    public int add(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {

        MoxieSNSBo moxieSNSBo = new MoxieSNSBo();
        //根据用户userDataId获取用户基本信息
        moxieSNSBo.setFid(IdGenerator.getId(CollectionName.MOXIESNS_DATA));
        moxieSNSBo.setName(name);
        moxieSNSBo.setAadhaarNo(aadhaarNo);
        moxieSNSBo.setMobile(mobile);
        moxieSNSBo.setEmail(email);
        moxieSNSBo.setDeviceFingerprint(deviceFingerprint);
        moxieSNSBo.setMetaData(originalData);
        moxieSNSBo.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        mongoUtil.insert(CollectionName.MOXIESNS_DATA, moxieSNSBo);
        return moxieSNSBo.getFid();
    }

    private Map<String,Object> parseStringToMap(String requestParam) {
        //请求参数去除特殊符号
        String inputParam = requestParam.replace("{","").replace("}","").replace("=",",");
        Map<String,Object> testMap = new HashMap<>(16);
        //将转换为map
        String[] test = inputParam.split(",");
        for(int i=0;i<test.length;i++){
            if(test[i].contains("url")){
                testMap.put("url",test[i+1]);
                continue;
            }
            if(test[i].contains("partner_code")){
                testMap.put("partner_code",test[i+1]);
                continue;
            }
            if(test[i].contains("partner_key")){
                testMap.put("partner_key",test[i+1]);
                continue;
            }
            if(test[i].contains("moxie_time_out")){
                testMap.put("moxie_time_out",test[i+1]);
                continue;
            }
            if(test[i].contains("task_id")){
                testMap.put("task_id",test[i+1]);
                continue;
            }
        }
        return testMap;
    }

}
