package com.adpanshi.cashloan.data.appdata;

import com.adpanshi.cashloan.data.appdata.bo.AppDataBo;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import org.testng.annotations.Test;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class AppUserBaseInfoDataBoTest {

    private AppDataDomain remote = RemoteFactory.getRemote(AppDataDomain.class, "1.0.0");

    @Test
    public void appDataTest(){
        String originalData="{ \"marriageState\":0, \"residentProvince\":\"浙江\", \"residentCity\": \"杭州\", \"residentAddress\": \"祥园路\", \"employer\": \"盘石\", \"emergencyContactName\": \"刘\", \"emergencyContactRel\": \"自己\", \"lastCity\": \"杭州\" }";
        remote.addAppUserBaseInfoData("周善文","362529199008212513","zsw","djflwejlepo","",  originalData);

    }

    @Test
    public void getDataTest(){
        AppDataBo appDataBo=remote.getAppUserBaseInfoData(55);
        System.out.println(appDataBo.getAccount());
        System.out.println(appDataBo.getAadhaarNo());
        System.out.println(appDataBo.getName());
        System.out.println(appDataBo.getOriginalData());
        System.out.println(appDataBo.getFid());
        System.out.println(appDataBo.getCreateTime());

    }


}
