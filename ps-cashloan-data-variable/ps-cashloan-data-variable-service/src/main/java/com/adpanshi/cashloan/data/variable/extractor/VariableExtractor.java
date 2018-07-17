package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.variable.model.VariableDataValue;

import java.util.List;

/**
 * 变量抽取器接口
 * Created by zsw on 2018/6/29 0029.
 */
public interface VariableExtractor {
    /**
     * 执行数据抽取
     *
     * @param channelDataId 渠道的数据ID
     * @return 抽取结果主键
     */
    List<VariableDataValue> doExtract(Integer channelDataId);
}
