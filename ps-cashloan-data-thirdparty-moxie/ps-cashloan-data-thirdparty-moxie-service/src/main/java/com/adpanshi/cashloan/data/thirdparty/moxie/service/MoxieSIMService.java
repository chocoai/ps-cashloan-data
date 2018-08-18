package com.adpanshi.cashloan.data.thirdparty.moxie.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class MoxieSIMService {

    @Autowired
    private MongoUtil mongoUtil;

    /**
     * 获取一条数据
     */
    public MoxieSIMBo get(Integer fid) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", fid)});
        List<MoxieSIMBo> moxieSIMBoList = mongoUtil.find(CollectionName.MOXIESIM_DATA, bson, MoxieSIMBo.class);
        if (moxieSIMBoList.size() == 0) {
            return null;
        }
        if (moxieSIMBoList.size() > 1) {
            throw new BusinessException("获取魔盒SIM数据异常,id:" + fid);
        }
        return moxieSIMBoList.get(0);
    }

    /**
     * 添加一条数据
     */
    public int add(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String report){
        MoxieSIMBo moxieSIMBo = new MoxieSIMBo();
        //根据用户userDataId获取用户基本信息
        moxieSIMBo.setFid(IdGenerator.getId(CollectionName.MOXIESIM_DATA));
        moxieSIMBo.setName(name);
        moxieSIMBo.setAadhaarNo(aadhaarNo);
        moxieSIMBo.setMobile(mobile);
        moxieSIMBo.setEmail(email);
        moxieSIMBo.setDeviceFingerprint(deviceFingerprint);
        moxieSIMBo.setMetaData(report);
        moxieSIMBo.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        mongoUtil.insert(CollectionName.MOXIESIM_DATA, moxieSIMBo);
        return moxieSIMBo.getFid();
    }
}
