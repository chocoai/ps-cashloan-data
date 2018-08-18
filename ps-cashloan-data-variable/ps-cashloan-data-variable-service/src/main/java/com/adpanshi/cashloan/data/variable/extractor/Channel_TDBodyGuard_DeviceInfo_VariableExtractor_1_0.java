package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.tdbody.bo.TDBodyGuardBo;
import com.adpanshi.cashloan.data.thirdparty.tdbody.domain.TDBodyGuardDomain;
import com.adpanshi.cashloan.data.variable.exception.VariableException;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zsw on 2018/8/1 0001.
 * 同盾保镖设备信息变量提取
 */
@Service
public class Channel_TDBodyGuard_DeviceInfo_VariableExtractor_1_0 implements VariableExtractor {

    private static Logger log = LoggerFactory.getLogger(Channel_TDBodyGuard_DeviceInfo_VariableExtractor_1_0.class);

    @Resource
    private TDBodyGuardDomain tdBodyGuardDomain;


    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        //调用磨盒SIM卡信息原始数据接口
        TDBodyGuardBo tdBodyGuardBo = tdBodyGuardDomain.getTDBodyGuardData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(tdBodyGuardBo.getMetaData());
        VariableData variableData = new VariableData();
        addDeviceInfoUserBasicInfoVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集(设备信息:安卓端sdk 3.x指纹明细)
     */
    private void addDeviceInfoUserBasicInfoVariable(JSONObject jsonObj, VariableData variableData) {

        log.info("用户【同盾信贷保镖设备信息】解析开始："+variableData.getMobile());

        //从最大json包获取data数据
        if (!jsonObj.containsKey("result_desc")) {
            return;
        }
        JSONObject data = jsonObj.getJSONObject("result_desc");
        //从data对象里取出task_data对象
        if (!data.containsKey("INFOANALYSIS")) {
            return;
        }
        JSONObject taskData = data.getJSONObject("INFOANALYSIS");
        //从task_data对象里取出profile对象
        if (!taskData.containsKey("device_info")) {
            return;
        }
        JSONObject profile = taskData.getJSONObject("device_info");

        try {
            if (profile.getString("fpVersion") != null) {
                variableData.addValueList("fpVersion", "集成sdk版本号", profile.getString("fpVersion"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("tokenId") != null) {
                variableData.addValueList("tokenId", "采集请求唯一标示", profile.getString("tokenId"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("deviceId") != null) {
                variableData.addValueList("deviceId", "设备ID", profile.getString("deviceId"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("os") != null) {
                variableData.addValueList("os", "操作系统的类型", profile.getString("os"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("sdkVersion") != null) {
                variableData.addValueList("sdkVersion", "Android设备API版本", profile.getString("sdkVersion"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("releaseVersion") != null) {
                variableData.addValueList("releaseVersion", "Android发行版本", profile.getString("releaseVersion"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("model") != null) {
                variableData.addValueList("model", "设备型号", profile.getString("model"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("product") != null) {
                variableData.addValueList("product", "生产该产品的内部代号", profile.getString("product"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("brand") != null) {
                variableData.addValueList("brand", "手机品牌", profile.getString("brand"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("serialNo") != null) {
                variableData.addValueList("serialNo", "厂商分配的设备序列号", profile.getString("serialNo"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("display") != null) {
                variableData.addValueList("display", "手机固件的编号", profile.getString("display"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("host") != null) {
                variableData.addValueList("host", "编译ROM的主机", profile.getString("host"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("deviceName") != null) {
                variableData.addValueList("deviceName", "出厂时的设备名称", profile.getString("deviceName"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("hardware") != null) {
                variableData.addValueList("hardware", "硬件平台名称或者代号", profile.getString("hardware"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("tags") != null) {
                variableData.addValueList("tags", "ROM的标签", profile.getString("tags"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("telephonyInfos") != null) {
                variableData.addValueList("telephonyInfos", "多个信息", profile.getString("telephonyInfos"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("deviceSVN") != null) {
                variableData.addValueList("deviceSVN", "设备的内部SVN号", profile.getString("deviceSVN"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("wifiIp") != null) {
                variableData.addValueList("wifiIp", "当前连接的无线网络的本地IP地址", profile.getString("wifiIp"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("wifiMac") != null) {
                variableData.addValueList("wifiMac", "无线网卡的mac地址", profile.getString("wifiMac"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("ssid") != null) {
                variableData.addValueList("ssid", "当前连接的无线网络名称", profile.getString("ssid"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("bssid") != null) {
                variableData.addValueList("bssid", "当前连接的无线网络的BSSID", profile.getString("bssid"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("gateway") != null) {
                variableData.addValueList("gateway", "无线网络的网关地址", profile.getString("gateway"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("wifiNetmask") != null) {
                variableData.addValueList("wifiNetmask", "无线网络的子网掩码", profile.getString("wifiNetmask"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("proxyInfo") != null) {
                variableData.addValueList("proxyInfo", "当前手机配置的HTTP代理IP和端口", profile.getString("proxyInfo"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("dnsAddress") != null) {
                variableData.addValueList("dnsAddress", "当前活动网络的DNS地址", profile.getString("dnsAddress"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("vpnIp") != null) {
                variableData.addValueList("vpnIp", "当前VPN连接的本地IP地址", profile.getString("vpnIp"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("vpnNetmask") != null) {
                variableData.addValueList("vpnNetmask", "当前VPN连接的子网掩码", profile.getString("vpnNetmask"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("cellIp") != null) {
                variableData.addValueList("cellIp", "2G/3G/4G网络连接的本地IP地址", profile.getString("cellIp"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("networkType") != null) {
                variableData.addValueList("networkType", "当前使用的网络连接类型", profile.getString("networkType"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("currentTime") != null) {
                variableData.addValueList("currentTime", "采集时的当前时间戳(毫秒)", profile.getString("currentTime"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("upTime") != null) {
                variableData.addValueList("upTime", "从开机到目前的毫秒数，包括休眠时间", profile.getString("upTime"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("bootTime") != null) {
                variableData.addValueList("bootTime", "开机时刻的时间戳(毫秒)", profile.getString("bootTime"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("activeTime") != null) {
                variableData.addValueList("activeTime", "从开机到目前的毫秒数，不包括休眠时间", profile.getString("activeTime"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("root") != null) {
                variableData.addValueList("root", "是否ROOT", profile.getString("root"), DataTypeEnum.BOOLEAN.getValue());
            }
            if (profile.getString("packageName") != null) {
                variableData.addValueList("packageName", "应用包名", profile.getString("packageName"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("apkVersion") != null) {
                variableData.addValueList("apkVersion", "应用版本号", profile.getString("apkVersion"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("packageName") != null) {
                variableData.addValueList("packageName", "应用包名", profile.getString("packageName"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("sdkMD5") != null) {
                variableData.addValueList("sdkMD5", "SDK core文件的md5", profile.getString("sdkMD5"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("signMD5") != null) {
                variableData.addValueList("signMD5", "APK签名文件的md5", profile.getString("signMD5"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("apkMD5") != null) {
                variableData.addValueList("apkMD5", "APK文件的md5", profile.getString("apkMD5"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("timeZone") != null) {
                variableData.addValueList("timeZone", "当前时区", profile.getString("timeZone"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("language") != null) {
                variableData.addValueList("language", "当前配置的语言", profile.getString("language"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("brightness") != null) {
                variableData.addValueList("brightness", "当前屏幕亮度", profile.getString("brightness"), DataTypeEnum.INTEGER.getValue());
            }
            if (profile.getString("batteryStatus") != null) {
                variableData.addValueList("batteryStatus", "当前充电状态", profile.getString("batteryStatus"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("batteryLevel") != null) {
                variableData.addValueList("batteryLevel", "电池电量", profile.getString("batteryLevel"), DataTypeEnum.DOUBLE.getValue());
            }
            if (profile.getString("batteryTemp") != null) {
                variableData.addValueList("batteryTemp", "电池当前的摄氏温度", profile.getString("batteryTemp"), DataTypeEnum.DOUBLE.getValue());
            }
            if (profile.getString("screenRes") != null) {
                variableData.addValueList("screenRes", "屏幕分辨率", profile.getString("screenRes"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("fontHash") != null) {
                variableData.addValueList("fontHash", "字体列表HASH", profile.getString("fontHash"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("blueMac") != null) {
                variableData.addValueList("blueMac", "蓝牙MAC地址", profile.getString("blueMac"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("androidId") != null) {
                variableData.addValueList("androidId", "系统初始化时随机生成的ID", profile.getString("androidId"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("cpuFrequency") != null) {
                variableData.addValueList("cpuFrequency", "CPU的最大频率", profile.getString("cpuFrequency"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("cpuHardware") != null) {
                variableData.addValueList("cpuHardware", "CPU硬件架构", profile.getString("cpuHardware"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("cpuType") != null) {
                variableData.addValueList("cpuType", "CPU的型号或者平台", profile.getString("cpuType"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("totalMemory") != null) {
                variableData.addValueList("totalMemory", "总内存字节数", profile.getString("totalMemory"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("availableMemory") != null) {
                variableData.addValueList("availableMemory", "可用内存字节数", profile.getString("availableMemory"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("totalStorage") != null) {
                variableData.addValueList("totalStorage", "总存储空间字节数", profile.getString("totalStorage"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("availableStorage") != null) {
                variableData.addValueList("availableStorage", "可用存储空间字节数", profile.getString("availableStorage"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("basebandVersion") != null) {
                variableData.addValueList("basebandVersion", "基带版本", profile.getString("basebandVersion"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("kernelVersion") != null) {
                variableData.addValueList("kernelVersion", "底层Linux内核版本", profile.getString("kernelVersion"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("gpsLocation") != null) {
                variableData.addValueList("gpsLocation", "当前GPS坐标", profile.getString("gpsLocation"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("allowMockLocation") != null) {
                variableData.addValueList("allowMockLocation", "是否允许位置模拟", profile.getString("allowMockLocation"), DataTypeEnum.BOOLEAN.getValue());
            }
            if (profile.getString("trueIp") != null) {
                variableData.addValueList("trueIp", "sdk采集的IP地址", profile.getString("trueIp"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("appOs") != null) {
                variableData.addValueList("appOs", "设备类型", profile.getString("appOs"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("error") != null) {
                variableData.addValueList("error", "错误原因", profile.getString("error"), DataTypeEnum.STRING.getValue());
            }
        }catch (Exception e) {
            log.error("【同盾信贷保镖设备信息】变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}
