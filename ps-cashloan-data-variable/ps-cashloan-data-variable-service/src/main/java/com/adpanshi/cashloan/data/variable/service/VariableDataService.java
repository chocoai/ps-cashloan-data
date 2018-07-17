package com.adpanshi.cashloan.data.variable.service;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.common.mongo.beanUtil.MongoDocumentKit;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.data.variable.exception.VariableException;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class VariableDataService{
    private static final Logger logger = LoggerFactory.getLogger(VariableDataService.class);

    @Autowired
    private MongoUtil mongoUtil;
    @Value("${mongo.database}")
    private String database;

    /**
     * 根据变量Id查询单条数据
     * @param dataId
     * @return
     */
    public VariableData findDataById(Integer dataId){
        Bson bson = Filters.and(new Bson[]{Filters.eq("fid", dataId)});
        List<VariableData> variableData = null;
        try{
            variableData  = mongoUtil.find(CollectionName.VARIABLE_DATA, bson, VariableData.class);
        }catch (Exception e){
            logger.error("根据变量dataId:" + dataId + ", 查询到数据异常");
            throw new VariableException("根据变量dataId:" + dataId + ", 查询数据异常",e);
        }
        if (variableData.size() > 1){
            logger.error("根据变量dataId:" + dataId + ", 查询到数据异常");
            throw new VariableException("根据变量dataId:" + dataId + ", 查询到多条数据,数据异常");
        }
        return variableData.isEmpty() ? null : variableData.get(0);
    }
    public Integer insertOne(VariableData variableData){
        Document s = Document.parse(JSON.toJSONString(variableData));
        mongoUtil.getMongoClient().getDatabase(database).getCollection(CollectionName.VARIABLE_DATA).insertOne(MongoDocumentKit.toDocument(s));
        return variableData.getFid();
    }

}
