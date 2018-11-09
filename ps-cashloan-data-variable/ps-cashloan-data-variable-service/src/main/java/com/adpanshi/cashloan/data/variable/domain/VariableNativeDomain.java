package com.adpanshi.cashloan.data.variable.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adpanshi.cashloan.data.variable.extractor.VariableExtractor;
import com.adpanshi.cashloan.data.variable.factory.VariableExtractorFactory;
import com.adpanshi.cashloan.data.variable.model.DataFrom;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import com.adpanshi.cashloan.data.variable.model.VariableExtractConfig;
import com.adpanshi.cashloan.data.variable.service.VariableDataService;
import com.adpanshi.cashloan.data.variable.service.VariableExtractConfigService;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import org.springframework.stereotype.Service;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service("variableDomain")
public class VariableNativeDomain implements VariableDomain{

    @Resource
    private VariableExtractorFactory variableExtractorFactory;
    @Resource
    private VariableExtractConfigService variableExtractConfigService;
    @Resource
    private VariableDataService variableDataService;

    @Override
    public List<VariableDataBo> extractVariable(ChannelType channelType, ChannelBizType channelBizType, Integer channelDataId,
                                                String channelDataCreateTime, String mobile, String email, String aadhaarNo,
                                                String name, String equipmentFingerpints) {
        List<VariableExtractConfig> configList = variableExtractConfigService.findByChannelTypeAndChannelBizType(channelType, channelBizType);
        DataFrom dataFrom = new DataFrom();
        dataFrom.setChannelType(channelType.getValue());
        dataFrom.setChannelBizType(channelBizType.getValue());
        dataFrom.setChannelDataId(channelDataId);
        dataFrom.setCreateTime(channelDataCreateTime);
        List<VariableData> variableDataList = new ArrayList<>();
        for (VariableExtractConfig config : configList) {
            VariableExtractor variableExtractor = variableExtractorFactory.getVariableExtractor(config.getVariableExtractVersion());
            List<VariableDataValue> valueList = variableExtractor.doExtract(channelDataId);
            VariableData variableData = new VariableData();
            variableData.setAadhaarNo(aadhaarNo);
            variableData.setMobile(mobile);
            variableData.setEmail(email);
            variableData.setName(name);
            variableData.setEquipmentFingerpints(equipmentFingerpints);
            variableData.setVariableType(config.getVariableType());
            variableData.setVariableExtractVersion(config.getVariableExtractVersion());
            variableData.setValueList(valueList);
            variableData.setDataFrom(dataFrom);
            variableData.setFid(IdGenerator.getId(CollectionName.VARIABLE_DATA));
            variableData.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
            variableDataService.insertOne(variableData);//TODO 想办法实现MongoDB批量插入
            variableDataList.add(variableData);
        }
        return BeanUtil.convertList(variableDataList, VariableDataBo.class);
    }


    @Override
    public List<VariableDataBo> findVariableList(List<Integer> variableDataIdList) {
        List<VariableDataBo> list = new ArrayList<>();
        for (int i = 0; i < variableDataIdList.size(); i++) {
            Integer dataId = variableDataIdList.get(i);
            VariableData variableData = variableDataService.findDataById(dataId);
            list.add(BeanUtil.convert(variableData, VariableDataBo.class));
        }
        return list;
    }

    @Override
    public VariableDataBo getVariableFindById(Integer variableDataId) {
        VariableData variableData = variableDataService.findDataById(variableDataId);
        return BeanUtil.convert(variableData, VariableDataBo.class);
    }
}
