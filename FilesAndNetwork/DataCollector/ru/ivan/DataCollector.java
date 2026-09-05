package ru.ivan;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class DataCollector {
    String path;

    public List<String> getListOfJson(String path) {
        SearchFiles searchFiles = new SearchFiles();
        List<String> listOfJson = new ArrayList<String>();
        Map<String, List<String>> pathsFromSearchFiles = searchFiles.searchFiles(path);
        for (String key : pathsFromSearchFiles.keySet()) {
            if (key.contains("json")) {
                listOfJson.add(String.valueOf(pathsFromSearchFiles.get(key)));
            }
        }
        return listOfJson;
    }

    public List<String> getListOfCsv(String path) {
        SearchFiles searchFiles = new SearchFiles();
        List<String> listOfCsv = new ArrayList<String>();
        Map<String, List<String>> pathsFromSearchFiles = searchFiles.searchFiles(path);
        for (String key : pathsFromSearchFiles.keySet()) {
            if (key.contains("csv")) {
                listOfCsv.add(String.valueOf(pathsFromSearchFiles.get(key)));
            }
        }
        return listOfCsv;
    }

    public DataCollector() {
    }

    public DataCollector(String path) {
        this.path = path;
    }
}