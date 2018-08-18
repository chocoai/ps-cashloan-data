package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zsw on 2018/8/2 0002.
 */
@Service
public class Channel_App_CrossValidationMaster_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger logger = LoggerFactory.getLogger(Channel_App_CrossValidationMaster_VariableExtractor_1_0.class);


    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        return null;
    }
}
