package com.adpanshi.cashloan.data.feature.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataValueBo;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.feature.extractor.FeatureExtractor;
import com.adpanshi.cashloan.data.feature.factory.FeatureExtractorFactory;
import com.adpanshi.cashloan.data.feature.model.DataFrom;
import com.adpanshi.cashloan.data.feature.model.FeatureData;
import com.adpanshi.cashloan.data.feature.model.FeatureExtractConfig;
import com.adpanshi.cashloan.data.feature.pojo.FeatureDataValue;
import com.adpanshi.cashloan.data.feature.pojo.FeatureExtractSimpleResultBo;
import com.adpanshi.cashloan.data.feature.service.FeatureDataService;
import com.adpanshi.cashloan.data.feature.service.FeatureExtractConfigService;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import com.adpanshi.cashloan.idgenerator.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service("featureDomain")
public class FeatureNativeDomain implements FeatureDomain {

    @Resource
    private FeatureExtractorFactory featureExtractorFactory;
    @Resource
    private FeatureExtractConfigService featureExtractConfigService;
    @Resource
    private FeatureDataService featureDataService;

    @Override
    public FeatureDataBo getFeatureDataById(Integer dataId) {
        FeatureData featureData = featureDataService.findDataById(dataId);
        FeatureDataBo featureDataBo = new FeatureDataBo();
        featureDataBo.setFid(featureData.getFid());
        featureDataBo.setMobile(featureData.getMobile());
        featureDataBo.setAadhaarNo(featureData.getAadhaarNo());
        featureDataBo.setName(featureData.getName());
        List<DataFromBo> dataFromBos = new ArrayList<>();
        List<FeatureDataValueBo> featureDataValueBos = new ArrayList<>();
        DataFromBo dataFromBo = new DataFromBo();
        for (DataFrom dataFrom : featureData.getDataFromList()) {
            dataFromBo.setVariableDataId(dataFrom.getVariableDataId());
            if (dataFrom.getVariableType() != null) {
                dataFromBo.setVariableType(VariableType.getByValue(dataFrom.getVariableType()));
            }
            dataFromBos.add(dataFromBo);
        }
        featureDataBo.setDataFromList(dataFromBos);
        for (FeatureDataValue featureDataValue : featureData.getValueList()) {
            FeatureDataValueBo featureDataValueBo = new FeatureDataValueBo();
            featureDataValueBo.setKey(featureDataValue.getKey());
            featureDataValueBo.setContent(featureDataValue.getContent());
            featureDataValueBo.setValue(featureDataValue.getValue());
            featureDataValueBo.setValueType(featureDataValue.getValueType());
            featureDataValueBos.add(featureDataValueBo);
        }
        featureDataBo.setValueList(featureDataValueBos);
        featureDataBo.setFeatureExtractVersion(featureData.getFeatureExtractVersion());
        featureDataBo.setFeatureType(FeatureType.getByValue(featureData.getFeatureType()));
        return featureDataBo;
    }

    @Override
    public List<FeatureDataBo> findFeatureList(List<Integer> featureDataIdList) {
        List<FeatureDataBo> list = new ArrayList<>();
        for (int i = 0; i < featureDataIdList.size(); i++) {
            Integer dataId = featureDataIdList.get(i);
            FeatureDataBo featureDataBo = this.getFeatureDataById(dataId);
            list.add(featureDataBo);
        }
        return list;
    }

    @Override
    public FeatureDataBo extractFeature(List<DataFromBo> dataFromBoList, FeatureType featureType, String mobile, String email, String aadhaarNo, String name, String equipmentFingerpints) {
        FeatureExtractConfig config = featureExtractConfigService.getByFeatureType(featureType);
        if (config == null) {
            return null;
        }
        //筛选出符合配置的变量
        List<DataFromBo> dataFromBoListToBeUse = new ArrayList<>();
        for (DataFromBo dataFromBo : dataFromBoList) {
            for (String variableTypeStr : config.getVariableTypeList()) {
                if (dataFromBo.getVariableType().getValue().equals(variableTypeStr)) {
                    dataFromBoListToBeUse.add(dataFromBo);
                }
            }
        }
        //找到合适的抽取器
        FeatureExtractor extractor = featureExtractorFactory.getFeatureExtractor(config.getFeatureExtractVersion());
        if (extractor == null) {
            throw new BusinessException("没有对应的特征抽取器,抽取器名称" + config.getFeatureExtractVersion());
        }
        FeatureExtractSimpleResultBo simpleResultBo = extractor.doExtract(dataFromBoListToBeUse);
        List<DataFrom> dataFromList = new ArrayList<>();
        for (DataFromBo d1 : dataFromBoListToBeUse) {
            for (Integer id : simpleResultBo.getDataFromVariableIdList()) {
                if (id.equals(d1.getVariableDataId())) {
                    dataFromList.add(BeanUtil.convert(d1, DataFrom.class));
                }
            }
        }
        FeatureData featureData = new FeatureData();
        featureData.setFid(IdGenerator.getId(CollectionName.FEATURE_DATA));
        featureData.setAadhaarNo(aadhaarNo);
        featureData.setMobile(mobile);
        featureData.setEmail(email);
        featureData.setName(name);
        featureData.setEquipmentFingerpints(equipmentFingerpints);
        featureData.setFeatureType(featureType.getValue());
        featureData.setFeatureExtractVersion(config.getFeatureExtractVersion());
        featureData.setCreatedTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        featureData.setValueList(simpleResultBo.getDataValueList());
        featureData.setDataFromList(dataFromList);
        featureDataService.insertOne(featureData);
        return BeanUtil.convert(featureData, FeatureDataBo.class);
    }
}