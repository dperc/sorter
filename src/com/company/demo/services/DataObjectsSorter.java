package com.company.demo.services;

import com.company.demo.model.DataObject;

import java.util.Collections;
import java.util.List;

public class DataObjectsSorter {

    private List<DataObject> listOfData;

    public DataObjectsSorter(List<DataObject> listOfData) {
        this.listOfData = listOfData;
    }

    /**
     * Order (asc) list of DataObjects starting with first column and continuing with the rest.
     * @return - ordered list.
     */
    public List<DataObject> sortData() {
        Collections.sort(listOfData, (object1, object2) -> {
            int compareResult = object1.getMatchId().compareTo(object2.getMatchId());
            if(compareResult != 0) {
                return compareResult;
            }

            compareResult = object1.getMarketId().compareTo(object2.getMarketId());
            if(compareResult != 0) {
                return compareResult;
            }

            compareResult = object1.getOutcomeId().compareTo(object2.getOutcomeId());
            if(compareResult != 0) {
                return compareResult;
            }

            compareResult = object1.getOutcomeId().compareTo(object2.getOutcomeId());
            return compareResult;

        });

        return listOfData;
    }
}
