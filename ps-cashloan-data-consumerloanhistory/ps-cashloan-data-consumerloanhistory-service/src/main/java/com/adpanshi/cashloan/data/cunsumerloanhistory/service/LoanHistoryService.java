package com.adpanshi.cashloan.data.cunsumerloanhistory.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.cunsumerloanhistory.pojo.LoanHistoryData;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zsw on 2018/8/4 0004.
 */
@Service
public class LoanHistoryService {

    @Resource
    private MongoUtil mongoUtil;

    /**
     * 添加一条用户贷款数据
     * @return 主键id
     */
    public Integer addCunsumenLoanHistoryData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData){

        LoanHistoryData loanHistoryData = new LoanHistoryData();
        loanHistoryData.setFid(IdGenerator.getId(CollectionName.CUNSUMER_LOAN_HISTORY_DATA));
        loanHistoryData.setName(name);
        loanHistoryData.setMobile(mobile);
        loanHistoryData.setEmail(email);
        loanHistoryData.setAadhaarNo(aadhaarNo);
        loanHistoryData.setOriginalData(originalData);
        loanHistoryData.setDeviceFingerprint(deviceFingerprint);
        String currentTime = DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat);
        loanHistoryData.setCreateTime(currentTime);

        mongoUtil.insert(CollectionName.CUNSUMER_LOAN_HISTORY_DATA, loanHistoryData);
        return  loanHistoryData.getFid();

    }

    /**
     * 根据ID取一条数据
     */
    public LoanHistoryData getCunsumenLoanHistoryData(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<LoanHistoryData> loanHistoryDataList = mongoUtil.find(CollectionName.CUNSUMER_LOAN_HISTORY_DATA, bson, LoanHistoryData.class);
        return loanHistoryDataList.isEmpty() ? null : loanHistoryDataList.get(0);
    }
}
