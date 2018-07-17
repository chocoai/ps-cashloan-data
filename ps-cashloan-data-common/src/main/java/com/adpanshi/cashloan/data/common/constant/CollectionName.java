package com.adpanshi.cashloan.data.common.constant;

/**
 * Created by zsw on 2018/6/29 0029.
 * Mongo collections 名称
 */
public interface CollectionName {

    /**
     * ID生成器
     */
    String ID_GENERATOR_CONFIG = "IdGeneratorConfig";

    /**
     * 清洗出的变量
     */
    String VARIABLE_DATA ="VariableData";

    /**
     * 清洗出的特征值
     */
    String FEATURE_DATA = "FeatureData";

    /**
     * 变量抽取器配置
     */
    String VARIABLE_EXTRACT_CONFIG = "VariableExtractConfig";

    /**
     * 特征抽取器配置
     */
    String FEATURE_EXTRACT_CONFIG = "FeatureExtractConfig";

    /**
     * 用户数据
     */
    String USER_DATA = "UserData";

    /**
     * app原始数据
     */
    String APP_DATA="AppData";
}
