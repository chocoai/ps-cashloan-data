package com.adpanshi.cashloan.data.feature.factory;

import com.adpanshi.cashloan.data.feature.extractor.FeatureExtractor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class FeatureExtractorFactory {
    private Map<String, FeatureExtractor> featureExtractorMap;

    public FeatureExtractor getFeatureExtractor(String featureExtractVersion){
        return featureExtractorMap.get(featureExtractVersion);
    }

    public Map<String, FeatureExtractor> getFeatureExtractorMap() {
        return featureExtractorMap;
    }

    public void setFeatureExtractorMap(Map<String, FeatureExtractor> featureExtractorMap) {
        this.featureExtractorMap = featureExtractorMap;
    }
}
