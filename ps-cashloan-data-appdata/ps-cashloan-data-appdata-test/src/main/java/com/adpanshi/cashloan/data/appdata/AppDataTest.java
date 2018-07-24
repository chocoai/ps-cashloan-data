package com.adpanshi.cashloan.data.appdata;

import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.bo.AppDataBo;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class AppDataTest {

    private AppDataDomain remote = RemoteFactory.getRemote(AppDataDomain.class, "1.0.0");

    @Test
    public void appDataTest(){
        String originalData="{ \"marriageState\":0, \"residentProvince\":\"浙江\", \"residentCity\": \"杭州\", \"residentAddress\": \"祥园路\", \"employer\": \"盘石\", \"emergencyContactName\": \"刘\", \"emergencyContactRel\": \"自己\", \"lastCity\": \"杭州\" }";
        remote.addAppData("周善文","362529199008212513","zsw","djflwejlepo","",  originalData);

    }

    @Test
    public void getDataTest(){
        AppDataBo appDataBo=remote.getAppData(1);
        System.out.println(appDataBo.getAccount());
        System.out.println(appDataBo.getAadhaarNo());
        System.out.println(appDataBo.getName());
        System.out.println(appDataBo.getOriginalData());
        System.out.println(appDataBo.getFid());
        System.out.println(appDataBo.getCreateTime());

    }


}
