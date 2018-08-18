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
     * 决策建议结果数据
     */
    String DECISION_DATA = "DecisionData";

    /**
     * app用户基本信息原始数据
     */
    String APP_USERBASEINFO_DATA ="AppUserBaseInfoData";

    /**
     * APP设备信息原始数据
     */
    String APP_EQUIPMENT_DATA = "AppEquipmentData";

    /**
     *app通讯录原始数据
     */
    String APP_COMMUNICATION_DATA = "AppCommunicationData";

    /**
     * app应用列表原始数据
     */
    String APP_APPLICATION_DATA = "AppApplicationData";
    /**
     * app紧急联系人原始数据
     */
    String APP_EMERGENCY_DATA = "AppEmergencyData";

    /**
     * 盘卡原始数据
     */
    String PANCARD_DATA = "PanCardData";

    /**
     * equifax信用报告数据
     */
    String EQUIFAX_CREDITREPORT_DATA = "EquifaxCreditReportData";

    /**
     * equifax信用报告数据日志
     */
    String EQUIFAX_CREDITREPORT_DATA_LOG = "EquifaxCreditReportDataLog";

    /**
     * 磨盒SIM数据
     */
    String MOXIESIM_DATA = "MoxieSIMData";

    /**
     * 磨盒社交数据
     */
    String MOXIESNS_DATA = "MoxieSNSData";

    /**
     * 用户贷款历史数据
     */
    String CUNSUMER_LOAN_HISTORY_DATA = "CunsumerLoanHistoryData";
    /**
     * 用户通话记录原始数据
     */
    String APP_CALLRECORD_DATA = "AppCallRecordData";

    /**
     * 同盾信贷数据
     */
    String TDBODYGUARD_DATA = "TdBodyGuardData";
}
