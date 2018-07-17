package com.adpanshi.cashloan.data.feature.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.mongo.beanUtil.MongoDocumentKit;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.feature.model.FeatureData;
import com.adpanshi.cashloan.data.variable.exception.VariableException;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.model.Filters;
import org.bson.Document;
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
public class FeatureDataService {
    private static final Logger logger = LoggerFactory.getLogger(FeatureDataService.class);

    @Resource
    public MongoUtil mongoUtil ;

    @Value("${mongo.database}")
    private String database;

    public Integer insertOne(FeatureData featureData){
        Document s = Document.parse(JSON.toJSONString(featureData));
        mongoUtil.getMongoClient().getDatabase(database).getCollection(CollectionName.FEATURE_DATA).insertOne(MongoDocumentKit.toDocument(s));
        return featureData.getFid();
    }

    /**
     * 根据变量Id查询单条数据
     * @param dataId
     * @return
     */
    public FeatureData findDataById(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<FeatureData> featureData = mongoUtil.find(CollectionName.FEATURE_DATA, bson, FeatureData.class);
        if (featureData.size() > 1){
            logger.error("根据特征dataId:" + dataId + ", 查询到多条数据,数据异常");
            throw new VariableException("根据特征dataId:" + dataId + ", 查询到多条数据,数据异常");
        }
        return featureData.isEmpty() ? null : featureData.get(0);
    }

}
