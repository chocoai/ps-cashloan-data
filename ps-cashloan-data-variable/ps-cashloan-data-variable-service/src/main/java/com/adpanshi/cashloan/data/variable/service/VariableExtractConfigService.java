package com.adpanshi.cashloan.data.variable.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;
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
     * @param channelTypeEnum 渠道类型
     * @param channelBizTypeEnum 渠道业务类型
     */
    public List<VariableExtractConfig> findByChannelTypeAndChannelBizType(ChannelTypeEnum channelTypeEnum, ChannelBizTypeEnum channelBizTypeEnum) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("channelType", channelTypeEnum.getValue()), Filters.eq("channelBizType", channelBizTypeEnum.getValue())});
        List<VariableExtractConfig> variableExtractConfigs =  mongoUtil.find(CollectionName.VARIABLE_EXTRACT_CONFIG, bson, VariableExtractConfig.class);
        return variableExtractConfigs.isEmpty() ? new ArrayList<VariableExtractConfig>() : variableExtractConfigs;
    }
}
