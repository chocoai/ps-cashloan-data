package com.adpanshi.cashloan.data.user.bo;


import com.adpanshi.cashloan.common.enums.ContentEnum;

import java.io.Serializable;

/**
 * Created by zsw on 2017/9/15.
 *
 * @Description:
 */
public class UserDataStatusBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private DataStatus dataStatus;
    private Integer dataId;

    public DataStatus getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(DataStatus dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public enum DataStatus implements ContentEnum {

        /**
         * 成功
         */
        SUCCESS("成功", 20),
        SKIP("跳过", 40);

        private String content;
        private Integer value;

        private DataStatus(String content, Integer value) {
            this.content = content;
            this.value = value;
        }
        @Override
        public boolean equalsValue(Integer value) {
            return (value != null) && (value.equals(getValue()));
        }

        public static DataStatus valueOf(Integer value) {
            DataStatus[] entities = DataStatus.values();
            for (DataStatus entity : entities) {
                if (entity.getValue().equals(value)) {
                    return entity;
                }
            }
            return null;
        }

        @Override
        public String getContent() {
            return this.content;
        }

        @Override
        public Integer getValue() {
            return this.value;
        }
    }

    public UserDataStatusBo(){
        super();
    }
    public UserDataStatusBo(DataStatus dataStatus, Integer dataId){
        this.dataStatus = dataStatus;
        this.dataId = dataId;
    }
}
