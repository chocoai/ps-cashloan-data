package com.adpanshi.cashloan.data.variable.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.variable.model.VariableExtractConfig;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 变量抽取配置Service
 * Created by zsw on 2018/6/29 0029.
 */
@Service("variableExtractConfigService")
public class VariableExtractConfigService {

    @Resource
    private MongoUtil mongoUtil;


    /**
     *  根据渠道和渠道业务类型查找
     * @param channelType 渠道类型
     * @param channelBizType 渠道业务类型
     */
    public List<VariableExtractConfig> findByChannelTypeAndChannelBizType(ChannelType channelType, ChannelBizType channelBizType) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("channelType", channelType.getValue()), Filters.eq("channelBizType", channelBizType.getValue())});
        List<VariableExtractConfig> variableExtractConfigs =  mongoUtil.find(CollectionName.VARIABLE_EXTRACT_CONFIG, bson, VariableExtractConfig.class);
        return variableExtractConfigs.isEmpty() ? new ArrayList<VariableExtractConfig>() : variableExtractConfigs;
    }
}
