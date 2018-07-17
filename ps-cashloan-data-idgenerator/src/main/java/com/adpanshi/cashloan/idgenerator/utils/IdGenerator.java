package com.adpanshi.cashloan.idgenerator.utils;

import java.util.concurrent.atomic.AtomicInteger;

import com.adpanshi.cashloan.common.mongo.MongoUtil;
import com.adpanshi.cashloan.data.common.constant.CollectionName;
import com.adpanshi.cashloan.idgenerator.exception.IdGeneratorException;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class IdGenerator {
    /**
     * 封装的mongo帮助类
     */
    private static MongoUtil mongoUtil;
    /**
     * db名称
     */
    private static String database;

    private IdGenerator(){}

    /**
     * ID自增
     * @param code 配置的Collections 名字
     * @return ID
     */
    public final static Integer getId(String code){

        if (StringUtils.isEmpty(code)){
            throw new IdGeneratorException("ID生成器类型不能为空");
        }

        MongoClient mongoClient = mongoUtil.getMongoClient();
        MongoDatabase mongoDb = mongoClient.getDatabase(database);
        MongoCollection<Document> collection = mongoDb.getCollection(CollectionName.ID_GENERATOR_CONFIG);

        Bson query = Filters.and(new Bson[]{Filters.eq("code", code)});
        Bson update = Filters.and(new Bson[]{Filters.eq("$inc", new Document("seq", 1))});

        Document result = collection.findOneAndUpdate(query,update);
        if (result == null){
            return null;
        }

        AtomicInteger currentId = new AtomicInteger(result.getInteger("seq"));
        return currentId.addAndGet(1);
    }

    @Autowired
    public IdGenerator(MongoUtil mongoUtil){
        IdGenerator.mongoUtil = mongoUtil;
    }

    @Value("${mongo.database}")
    public void setDatabase(String database) {
        IdGenerator.database = database;
    }
}
