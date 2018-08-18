package com.adpanshi.cashloan.data.feature.domain;

import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataBo;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;

import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public interface FeatureDomain {
    /**
     * 同步抽取特征
     * @param variableDataIdList 变量数据List
     * @param featureType       特征类型
     * @return                  特征抽取结果
     */
    FeatureDataBo extractFeature(List<DataFromBo> variableDataIdList, FeatureType featureType, String mobile, String email,
                                 String aadhaarNo, String name, String equipmentFingerpints);

    /**
     * 根据id获取特征数据
     * @param dataId
     * @return
     */
    FeatureDataBo getFeatureDataById(Integer dataId);

    /**
     * 根据ID从数据库查询变量
     * @param featureDataIdList 特征ID集合
     * @return monggo中取出的变量LIST
     */
    List<FeatureDataBo> findFeatureList(List<Integer> featureDataIdList);
}
