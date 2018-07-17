package com.adpanshi.cashloan.data.feature.extractor;

import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.pojo.FeatureExtractSimpleResultBo;

import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public interface FeatureExtractor  {
    /**
     * 执行数据抽取
     *
     * @param variableDataIdList 变量数据ID
     * @return 抽取结果主键
     */
    FeatureExtractSimpleResultBo doExtract(List<DataFromBo> variableDataIdList);
}
