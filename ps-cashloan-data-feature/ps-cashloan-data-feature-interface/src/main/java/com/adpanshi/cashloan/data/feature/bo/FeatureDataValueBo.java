package com.adpanshi.cashloan.data.feature.bo;

import java.io.Serializable;

/**
 *  Created by zsw on 2018/6/29 0029.
 */
public class FeatureDataValueBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private Object value;
    private Integer valueType;
    private String content;

    public FeatureDataValueBo(){}

    public FeatureDataValueBo(String key, String content, Object value, Integer valueType){
        this.setKey(key);
        this.setContent(content);
        this.setValue(value);
        this.setValueType(valueType);
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if(value==null)
            value="";
        this.value = value;
    }

}
