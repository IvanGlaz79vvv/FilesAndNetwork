package ru.ivan;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SearchFiles {
    List<String> listOfCsv = new ArrayList<>();
    List<String> listOfJson  = new ArrayList<>();
    Map<String, List<String>> mapOfSearchFiles = new HashMap<>();

    public Map<String, List<String>> searchFiles(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            recursiveListFiles(file);
        }
        mapOfSearchFiles.put("csv", listOfCsv);
        mapOfSearchFiles.put("json", listOfJson);
        return mapOfSearchFiles;
    }

    private void recursiveListFiles(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = null;
                if (file.isDirectory()) {
                    recursiveListFiles(file);
                } else {
                    fileName = file.getName();
                    if (fileName.endsWith(".csv")) {
                        listOfCsv.add(file.toString());
                    } else {
                        fileName = file.getName();
                        if (fileName.endsWith(".json")) {
                            listOfJson.add(file.toString());
                        }
                    }
                }
            }
        }
    }
}