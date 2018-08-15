package com.company.demo.services;

import com.company.demo.model.DataObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DataReaderService {

    private final String fileName;

    private static boolean firstLine;

    private static List<DataObject> listOfData;

    public DataReaderService(String fileName) {
        this.fileName = fileName;
        this.firstLine = true;
        this.listOfData = new LinkedList();
    }

    /**
     * Read lines from the input file and parse them.
     * @return - List of read DataObjects.
     */
    public List<DataObject> getDataObjectsFromFile() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> parseLine(line));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfData;
    }

    /**
     * Split line of text by character '|' create DataObject and insert it into the list of created DataObjectes.
     * @param line - one line of input file which is splitted.
     */
    private void parseLine(String line) {
        // first line is column header, skip it
        if(firstLine) {
            firstLine = false;
            return;
        }

        String[] splittedLine = line.split("\\|");

        DataObject dataObject;

        if(splittedLine.length == 3) {
            dataObject = new DataObject(
                    splittedLine[0].substring(1, splittedLine[0].length() - 1),
                    Long.valueOf(splittedLine[1]),
                    splittedLine[2].substring(1, splittedLine[2].length() - 1));

        } else if (splittedLine.length == 4) {
            dataObject = new DataObject(
                    splittedLine[0].substring(1, splittedLine[0].length() - 1),
                    Long.valueOf(splittedLine[1]),
                    splittedLine[2].substring(1, splittedLine[2].length() - 1),
                    splittedLine[3].substring(1, splittedLine[3].length() - 1));

            // specifiers have some | in the string, join the rest together
        } else {
            dataObject = new DataObject(
                    splittedLine[0].substring(1, splittedLine[0].length() - 1),
                    Long.valueOf(splittedLine[1]),
                    splittedLine[2].substring(1, splittedLine[2].length() - 1),
                    line.substring(splittedLine[0].length() + splittedLine[1].length() + splittedLine[2].length() + 4, line.length() - 1));
        }

        listOfData.add(dataObject);
    }
}
