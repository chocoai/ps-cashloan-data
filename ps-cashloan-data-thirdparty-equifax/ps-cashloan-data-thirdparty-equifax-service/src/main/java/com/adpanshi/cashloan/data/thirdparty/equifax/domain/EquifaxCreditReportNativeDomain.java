package com.adpanshi.cashloan.data.thirdparty.equifax.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.common.utils.HttpClientUtil;
import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;
import com.adpanshi.cashloan.data.thirdparty.equifax.constant.EquifaxMessageConstant;
import com.adpanshi.cashloan.data.thirdparty.equifax.exception.EquifaxReportException;
import com.adpanshi.cashloan.data.thirdparty.equifax.pojo.EquifaxReportData;
import com.adpanshi.cashloan.data.thirdparty.equifax.pojo.equifaxreport.Envelope;
import com.adpanshi.cashloan.data.thirdparty.equifax.service.EquifaxCreditReportService;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service("equifaxCreditReportDomain")
public class EquifaxCreditReportNativeDomain implements EquifaxCreditReportDomain {
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(EquifaxCreditReportNativeDomain.class);

    @Resource
    private EquifaxCreditReportService equifaxCreditReportService;

    @Value("${equifax.report.url}")
    private String equifaxReportUrl;

    @Value("${equifax.report.key}")
    private String equifaxReportKey;

    /**
     * 获取信用报告信息
     */
    @Override
    public String sendRequest(String requestParams) {
        try{
            JSONObject paramsJson = JSONObject.parseObject(requestParams);
            if (!paramsJson.containsKey("firstName") || !paramsJson.containsKey("lastName") || !paramsJson.containsKey("panNo")) {
                throw new RuntimeException("获取equifax信用报告失败，原因:必要参数为空！params = " + requestParams);
            }
            String panNo = paramsJson.getString("panNo");
            String firstName = paramsJson.getString("firstName");
            String lastName = paramsJson.getString("lastName");
            String responseResult = sendRequest(panNo, firstName, lastName);
//            String responseResult = readTxt("C:\\Users\\Administrator\\Desktop\\1_3_equifax_response.xml"); // TODO 测试用
            //TODO 对返回结果判断，失败抛出异常
            if(StringUtil.isBlank(responseResult)) {
                throw new EquifaxReportException(EquifaxMessageConstant.RESPONSE_RESULT_ERROR);
            }
            return responseResult;
        } catch (EquifaxReportException e) {
            logger.error("请求获取盘卡信息异常", e.getMessage());
            return null;
        }
    }

    @Override
    public EquifaxReportDataBo getMetaData(Integer dataId) {
        EquifaxReportData equifaxReportData = equifaxCreditReportService.getEquifaxReportData(dataId);
        String xmlMetaData = equifaxReportData.getOriginalData();
        equifaxReportData.setOriginalData(xmlConvertJsonStr(xmlMetaData));
        return BeanUtil.convert(equifaxReportData, EquifaxReportDataBo.class);
    }

    @Override
    public Integer save(String requestParams, String name, String aadhaarNo, String mobile, String email, String deviceFingerprint, String uuid, String originalData) {
        JSONObject paramsJson = JSONObject.parseObject(requestParams);
        if (!paramsJson.containsKey("firstName") || !paramsJson.containsKey("lastName") || !paramsJson.containsKey("panNo")) {
            throw new RuntimeException("获取equifax信用报告失败，原因:必要参数为空！params = " + requestParams);
        }
        String panNo = paramsJson.getString("panNo");
        String firstName = paramsJson.getString("firstName");
        String lastName = paramsJson.getString("lastName");
        return equifaxCreditReportService.addEquifaxCreditReporData(panNo, firstName, lastName, name, aadhaarNo, mobile, email, deviceFingerprint, uuid, originalData);
    }

    /**
     * 添加equifax信用报告原始数据
     *
     * @param mobile
     * @param email
     * @param status
     * @param uuid
     * @param requestParams
     * @param originalData
     * @return
     */
    @Override
    public Integer saveLog(String mobile, String email, String status, String uuid, String requestParams, String originalData) {
        return equifaxCreditReportService.addEquifaxCreditReporDataLog(mobile, email, status, uuid, requestParams, originalData);
    }

    private String sendRequest(String panNo, String firstName, String lastName) {
        Map sendMap = new HashMap<>(8);
        sendMap.put("first_name",firstName);
        sendMap.put("last_name",lastName);
        sendMap.put("pan_no",panNo);
        sendMap.put("key",equifaxReportKey);
        //发送pan卡接口
        return HttpClientUtil.sendPostRequest(equifaxReportUrl,sendMap,null,null);
    }

    private String xmlConvertJsonStr(String metaData) {
        //调用获取信用报告接口
        if(!StringUtil.isBlank(metaData)){
            StringBuffer sb = new StringBuffer();
            sb.append("<soapenv:Envelope>");
            sb.append("<soapenv:Body>");
            sb.append("<sch:InquiryResponse>");
            try {
                int i = metaData.indexOf("<sch:InquiryResponseHeader>");
                metaData = metaData.substring(i,metaData.length());
                XStream xs = new XStream();
                //xml节点对应实体类
                xs.alias("Envelope", Envelope.class);
                xs.processAnnotations(new Class[] { Envelope.class });
                xs.ignoreUnknownElements();
                sb.append(metaData);
                //通过这种方式把xml转成对象
                Object obj = xs.fromXML(sb.toString());
                return JSONObject.toJSONString(obj);
            }catch (Exception e){
                logger.error(e.getMessage());
                return null;
            }
        }
        return null;
    }
    private String readTxt(String pathname) {
        StringBuffer sb = new StringBuffer();
        try {
            // 要读取以上路径的input.txt文件
            File filename = new File(pathname);
            // 建立一个输入流对象reader
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            // 建立一个对象，它把文件内容转成计算机能读懂的语言
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                sb.append(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
