package com.adpanshi.cashloan.data.user;

import com.adpanshi.cashloan.common.enums.OrganizationEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.user.bo.*;
import com.adpanshi.cashloan.data.user.domain.UserDataDomain;
import com.adpanshi.cashloan.data.user.domain.UserDataNativeDomain;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;
import tool.util.StringUtil;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class DataUserTest {

    private UserDataDomain remote = RemoteFactory.getRemote(UserDataDomain.class, "1.0.0");


    private static final String MOBILE = "15267011679";
    private static final String AADHAAR_NO = "1234567890";
    private static final String EMAIL = "zhoushanwen8502@adpanshi.com";
    private static final String DEVICE_FINGERPRINT = "qwertyuiop";
    private static final Integer USER_DATA_ID = 2;
    private static final String ACCOUNT = "15267011679";
    private static final String NAME = "周善文";
    private static final Integer CHANNEL_DATA_ID = 3;
    private static final ChannelType CHANNEL_TYPE_ENUM = ChannelType.AADHAAR;
    private static final ChannelBizType CHANNEL_BIZ_TYPE_ENUM = ChannelBizType.APP_USER_BASE_INFO;
    /**
     * 创建用户数据
     */
    @Test
    public void create(){

        UserBaseDataBo userBaseDataBo = getUserBaseDataBoInstance();
        UserDataBo userDataBo = remote.create(userBaseDataBo);
        String userDataBoStr = JSONObject.toJSONString(userDataBo);
        assert !StringUtil.isBlank(userDataBoStr):userDataBoStr;
    }

    /**
     * 获取用户数据
     */
    @Test
    public void get() {
        UserDataBo userDataBo = remote.get(USER_DATA_ID);
        String userDataBoStr = JSONObject.toJSONString(userDataBo);
        System.err.println(userDataBoStr);
    }

    /**
     * 修改用户数据
     */
    @Test
    public void modify() {
        UserBaseDataBo userBaseDataBo = getUserBaseDataBoInstance();
        remote.modify(USER_DATA_ID, userBaseDataBo);

    }

    @Test
    public void oloanLoanApply() {
        String loanApplyResult = remote.oloanLoanApply(USER_DATA_ID);
        System.err.println(loanApplyResult);
    }

    @Test
    public void fillApplication() {
        String originalData = "\"[{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"All Banks IFSC Code\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.finananceindia.ifsccode\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"YONO by SBI\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sbi.lotusintouch\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Assistant\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.google.android.apps.googleassistant\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Citrus\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.citrus.citruspay\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Pixlr Express\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.pixlr.express\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Lifelog\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sonymobile.lifelog\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"NeoReader\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"de.gavitec.semc\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"WhatsApp\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.whatsapp\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"File Manager +\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.alphainventor.filemanager\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"GL Games\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.gameloft.android.magicbox\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Wisepilot for XPERIA™\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"org.microemu.android.se.appello.lp.WisepilotSE\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Amazon Shopping\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"in.amazon.mShop.android.shopping\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Kvb e-Book\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"kvbank.kvb_ebook\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"PDF Reader\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.kdanmobile.android.pdfreader.google.pad\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"OfficeSuite\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.mobisystems.editor.office_with_reg\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Block This!\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.savageorgiev.blockthis\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"SmartCoin\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"in.rebase.app\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Flipkart\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.flipkart.android\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"BHIM CUB eWallet\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.cub.wallet.gui\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"BHIM\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"in.org.npci.upiapp\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Google Go\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.google.android.apps.searchlite\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Google Play services for Instant Apps\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.google.android.instantapps.supervisor\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Outlook\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.microsoft.office.outlook\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"KVB Upay\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.mycompany.kvb\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Wishfin\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.mywish.wishfin.view\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Oloan\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.panshi.xjd\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"CUB mBank Plus\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.cub.plus.gui\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Browser (small app)\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sonymobile.smallbrowser\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"MX Player\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.mxtech.videoplayer.ad\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"SHAREit\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.lenovo.anyshare.gps\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"TANGEDCO\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.tneb.tangedco\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Internet Speed Meter Lite\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.internet.speed.meter.lite\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Paytm\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"net.one97.paytm\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"SlideME Marketplace\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.slideme.sam.manager\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"TNEB mobile\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.madroft.chan.tnebmobile\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Kerala Lottery Results\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.autofillwand.kerala_lottery_result\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Kissht\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.fastbanking\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"VLC\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"org.videolan.vlc\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"UC Browser\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.UCMobile.intl\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Adobe Acrobat\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.adobe.reader.intune\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"52 weeks\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.mobills.fiftytwoweeks\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Prime Video\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.amazon.avod.thirdpartyclient\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Games\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.gameloft.android.Widget.Games\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Dailyhunt\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.eterno\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Diagnostics\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sonymobile.androidapp.diagnostics\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"PlayStation®App\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.scee.psxandroid\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Status Downloader for Whatsapp\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.downlood.sav.whmedia\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"SBI Anywhere Personal\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sbi.SBIFreedomPlus\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Adobe Acrobat\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.adobe.reader\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Evernote\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.evernote\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"TrackID™\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sonyericsson.trackid\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Free PDF Reader - All in one PDF tools\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"pdfreader.pdfviewer.pdftools.free\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"LIC Customer\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.lic.liccustomer\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"CASHe\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"co.tslc.cashe.android\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Booking.com Hotels\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.booking\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"VidMate\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.nemo.vidmate\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"flash app\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"guide.flashplayerflv.freeusaapps\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"HDB On the Go\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.nucleus.finnone.mobile.mserve.hdb.eng\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"SBI Online\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sbi.onlineSbiMbl\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"دي ون جي\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.sonyericsson.d1gapp\\\",\\\"userId\\\":\\\"178\\\"},{\\\"iexpress\\\":\\\"0\\\",\\\"appName\\\":\\\"Flash Player For Android - Fast Plugin\\\",\\\"systemType\\\":\\\"2\\\",\\\"packageName\\\":\\\"com.playerappslabs.flashplugin\\\",\\\"userId\\\":\\\"178\\\"}]\"";
        Integer fillApplication = remote.fillApplication(USER_DATA_ID, originalData);
        System.out.println(fillApplication);
    }
    private UserVariableBo getUserVariableBoInstance() {
        UserVariableBo userVariableBo = new UserVariableBo();
        userVariableBo.setCreateTime("2018-07-15 22:02:24 112");
        userVariableBo.setVariableDataId(3);
        userVariableBo.setVariableExtractVersion("channel_App_UserBasicInfo_VariableExtractor_1_0");
        userVariableBo.setVariableType(VariableType.USER_BASICINFO);

        UserMetaDataBo userMetaDataBo = new UserMetaDataBo();
        userMetaDataBo.setChannelDataId(2);
        userMetaDataBo.setChannelBizType(ChannelBizType.APP_USER_BASE_INFO);
        userMetaDataBo.setChannelType(ChannelType.AADHAAR);
        userMetaDataBo.setCreateTime("2018-07-15 22:02:24 112");

        userVariableBo.setUserVariableDataFrom(userMetaDataBo);
        return userVariableBo;
    }
    private UserBaseDataBo getUserBaseDataBoInstance() {
        UserBaseDataBo userBaseDataBo = new UserBaseDataBo();
        userBaseDataBo.setAadhaarNo(AADHAAR_NO);
        userBaseDataBo.setCountryType(OrganizationEnum.CountryType.INDIA);
        userBaseDataBo.setProductType(OrganizationEnum.ProductType.OLOAN);
        userBaseDataBo.setDeviceFingerprint(DEVICE_FINGERPRINT);
        userBaseDataBo.setEmail(EMAIL);
        userBaseDataBo.setMobile(MOBILE);
        userBaseDataBo.setName(NAME);
        return userBaseDataBo;
    }
}
