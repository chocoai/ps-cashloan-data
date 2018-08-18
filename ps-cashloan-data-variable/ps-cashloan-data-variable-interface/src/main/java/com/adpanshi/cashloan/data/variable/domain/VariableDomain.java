package com.adpanshi.cashloan.data.variable.domain;

import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;

import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public interface VariableDomain {


    /**
     *　同步抽取变量
     * @param channelType           渠道类型
     * @param channelBizType        渠道业务类型
     * @param channelDataId         渠道数据主键
     * @param mobile               用户账号
     * @param aadhaarNo             用户身份证
     * @param name                  用户姓名
     * @param equipmentFingerpints  设备指纹
     */
    List<VariableDataBo> extractVariable(ChannelType channelType, ChannelBizType channelBizType, Integer channelDataId, String channelDataCreateTime, String mobile, String email, String aadhaarNo, String name, String equipmentFingerpints);

    /**
     * 根据ID从数据库查询变量
     * @param variableDataIdList 变量ID集合
     * @return monggo中取出的变量LIST
     */
    List<VariableDataBo> findVariableList(List<Integer> variableDataIdList);

    /**
     * 根据ID从数据库查询变量
     * @param variableDataId 变量ID
     * @return 根据ID从monggo中取出的变量
     */
    VariableDataBo getVariableFindById(Integer variableDataId);
}
