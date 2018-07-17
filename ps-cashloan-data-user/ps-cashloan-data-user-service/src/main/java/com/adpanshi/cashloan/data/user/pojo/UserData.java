package com.adpanshi.cashloan.data.user.pojo;

import java.util.List;

/**
 * 用户数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserData extends UserBaseData implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer fid;//主键
    private List<UserMetaData> userMetaDataList;//原始数据
    private List<UserVariable> userVariableList;//变量
    private List<UserFeature> userFeatureList;//特征
    private String createTime;
    private String lastModifyTime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public List<UserMetaData> getUserMetaDataList() {
        return userMetaDataList;
    }

    public void setUserMetaDataList(List<UserMetaData> userMetaDataList) {
        this.userMetaDataList = userMetaDataList;
    }

    public List<UserVariable> getUserVariableList() {
        return userVariableList;
    }

    public void setUserVariableList(List<UserVariable> userVariableList) {
        this.userVariableList = userVariableList;
    }

    public List<UserFeature> getUserFeatureList() {
        return userFeatureList;
    }

    public void setUserFeatureList(List<UserFeature> userFeatureList) {
        this.userFeatureList = userFeatureList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
