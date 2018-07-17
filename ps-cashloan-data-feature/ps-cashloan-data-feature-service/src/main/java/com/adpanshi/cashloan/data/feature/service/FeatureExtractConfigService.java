package com.adpanshi.cashloan.data.feature.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.feature.exception.FeatureException;
import com.adpanshi.cashloan.data.feature.model.FeatureExtractConfig;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class FeatureExtractConfigService {
    private static final Logger logger = LoggerFactory.getLogger(FeatureExtractConfigService.class);
    @Resource
    public MongoUtil mongoUtil;

    @Value("${mongo.database}")
    private String database;
    /**
     *  根据特征类别获取抽取配置(ps:一个特征类别只能有一个配置)
     */
    public FeatureExtractConfig getByFeatureType(FeatureType featureType) {
        Bson bson = Filters.and(new Bson[]{Filters.eq("featureType", featureType.getValue())});
        List<FeatureExtractConfig> FeatureExtractConfig = mongoUtil.find(CollectionName.FEATURE_EXTRACT_CONFIG, bson, FeatureExtractConfig.class);
        if (FeatureExtractConfig.size() > 1){
            logger.error("根据特征类别featureType:" + featureType + ", 查询到多条数据,数据异常");
            throw new FeatureException("根据特征类别featureType:" + featureType + ", 查询到多条数据,数据异常");
        }
        return FeatureExtractConfig.isEmpty() ? null : FeatureExtractConfig.get(0);
    }
}
