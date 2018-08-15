package com.company.demo;

import com.company.demo.model.DataObject;
import com.company.demo.services.DataObjectsSorter;
import com.company.demo.services.DataReaderService;
import com.company.demo.services.DbService;
import java.util.List;

public class Main {

    private static final String FILENAME = "./src/com/company/demo/resources/fo_random.txt";

    public static void main(String[] args) {
        DbService dbService = new DbService();
        dbService.initDbTable();

        DataReaderService dataReaderService = new DataReaderService(FILENAME);
        List<DataObject> listOfData = dataReaderService.getDataObjectsFromFile();

        DataObjectsSorter dataObjectsSorter = new DataObjectsSorter(listOfData);
        List<DataObject> sortedListOfData = dataObjectsSorter.sortData();

        dbService.insertData(sortedListOfData);
    }
}
