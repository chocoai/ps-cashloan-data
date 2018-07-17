package com.adpanshi.cashloan.data.variable.factory;


import com.adpanshi.cashloan.data.variable.extractor.VariableExtractor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 变量抽取器工厂
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class VariableExtractorFactory {

    private Map<String, VariableExtractor> variableExtractorMap;

    public VariableExtractor getVariableExtractor(String variableExtractVersion){
        return variableExtractorMap.get(variableExtractVersion);
    }

    public Map<String, VariableExtractor> getVariableExtractorMap() {
        return variableExtractorMap;
    }

    public void setVariableExtractorMap(Map<String, VariableExtractor> variableExtractorMap) {
        this.variableExtractorMap = variableExtractorMap;
    }
}
