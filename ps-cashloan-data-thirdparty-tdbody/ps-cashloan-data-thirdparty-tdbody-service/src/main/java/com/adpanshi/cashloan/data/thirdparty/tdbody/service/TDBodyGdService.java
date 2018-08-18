package com.adpanshi.cashloan.data.thirdparty.tdbody.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.thirdparty.tdbody.bo.TDBodyGuardBo;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tool.util.HttpUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class TDBodyGdService {

    @Autowired
    private MongoUtil mongoUtil;

    private HttpURLConnection conn;

    /**
     * 获取一条数据
     */
    public TDBodyGuardBo get(Integer fid) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", fid)});
        List<TDBodyGuardBo> moxieSIMBoList = mongoUtil.find(CollectionName.TDBODYGUARD_DATA, bson, TDBodyGuardBo.class);
        if (moxieSIMBoList.size() == 0) {
            return null;
        }
        if (moxieSIMBoList.size() > 1) {
            throw new BusinessException("获取同盾信贷保镖数据异常,id:" + fid);
        }
        return moxieSIMBoList.get(0);
    }

    /**
     * 添加一条数据
     */
    public Integer add(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String report){
        TDBodyGuardBo tdBodyGuardBo = new TDBodyGuardBo();
        //根据用户userDataId获取用户基本信息
        tdBodyGuardBo.setFid(IdGenerator.getId(CollectionName.TDBODYGUARD_DATA));
        tdBodyGuardBo.setName(name);
        tdBodyGuardBo.setAadhaarNo(aadhaarNo);
        tdBodyGuardBo.setMobile(mobile);
        tdBodyGuardBo.setEmail(email);
        tdBodyGuardBo.setDeviceFingerprint(deviceFingerprint);
        tdBodyGuardBo.setMetaData(report);
        tdBodyGuardBo.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        mongoUtil.insert(CollectionName.TDBODYGUARD_DATA, tdBodyGuardBo);
        return tdBodyGuardBo.getFid();
    }

    /**
     * 发送同盾信贷保镖请求获取数据
     * @param requestParam
     * @return
     */
    public String sendRequest(String requestParam){
        String result = null;
        try{
            //解析report信息
            Map<String,String> dataMap = new HashMap<>(16);
            Map<String,String> reportMap = JSON.parseObject(requestParam,Map.class);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(reportMap.get("url")));
            stringBuilder.append("?partner_code=").append(reportMap.get("partner_code")).append("&partner_key=").append(reportMap.get("partner_key")).append("&app_name=").append(reportMap.get("app_name")).toString();

            dataMap.put("biz_code",reportMap.get("biz_code"));
            dataMap.put("india_account_mobile",reportMap.get("india_account_mobile"));
            dataMap.put("india_id_number",reportMap.get("india_id_number"));
            dataMap.put("india_account_name",reportMap.get("india_account_name"));
            dataMap.put("black_box",reportMap.get("black_box"));
            result = HttpUtil.postClient(stringBuilder.toString(),dataMap);
        }catch(Exception e) {
            throw new RuntimeException("同盾信贷保镖查询异常, requestParam = " + requestParam + " , message = " + e.getMessage());
        }
        return result;
    }
}
