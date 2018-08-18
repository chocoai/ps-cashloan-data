package com.adpanshi.cashloan.data.cunsumerloanhistory.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.cunsumerloanhistory.bo.LoanHistoryDataBo;
import com.adpanshi.cashloan.data.cunsumerloanhistory.exception.CunsumerLoanHistoryDataException;
import com.adpanshi.cashloan.data.cunsumerloanhistory.pojo.LoanHistoryData;
import com.adpanshi.cashloan.data.cunsumerloanhistory.service.LoanHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/4 0004.
 */
@Service("loanHistoryDomain")
public class LoanHistoryNativeDomain implements LoanHistoryDomain {

    @Resource
    private LoanHistoryService loanHistoryService;

    @Override
    public Integer addCunsumerLoanHistoryData(String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String originalData) {
        return loanHistoryService.addCunsumenLoanHistoryData(name, aadhaarNo, mobile, email, deviceFingerprint, originalData);
    }

    @Override
    public LoanHistoryDataBo getCunsumerLoanHistoryData(Integer dataId) {
        LoanHistoryData loanHistoryData = loanHistoryService.getCunsumenLoanHistoryData(dataId);
        if (loanHistoryData == null) {
            throw new CunsumerLoanHistoryDataException("dataId="+dataId+"获取用户贷款历史数据为空");
        }
        return BeanUtil.convert(loanHistoryData, LoanHistoryDataBo.class);
    }
}
