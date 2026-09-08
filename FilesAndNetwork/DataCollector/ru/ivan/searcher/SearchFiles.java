package ru.ivan.searcher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchFiles {
    public Map<String, List<String>> searchFiles(String path, String extension) {
        Map<String, List<String>> mapOfSearchFiles = new HashMap<>();
        List<String> listOfFiles = new ArrayList<>();
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            recursiveListFiles(file, extension, listOfFiles);
        }
        mapOfSearchFiles.put(extension, listOfFiles);
        return mapOfSearchFiles;
    }

    private void recursiveListFiles(File dir, String extension, List<String> listOfFiles) {

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = null;
                if (file.isDirectory()) {
                    recursiveListFiles(file, extension, listOfFiles);
                } else {
                    fileName = file.getName();
                    if (fileName.endsWith("." + extension)) {
                        listOfFiles.add(file.toString());
                    }
                }
            }
        }
    }
}
