package ru.ivan;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class MySearchFiles {
    List<String> listOfMySearchFiles = new ArrayList<>();

    public List<String> searchMyFiles(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            recursiveListFiles(file);
        }
        return listOfMySearchFiles;
    }

    private  void recursiveListFiles(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    recursiveListFiles(file);
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".csv") || fileName.endsWith(".json"))
                    listOfMySearchFiles.add(file.toString());
                }
            }
        }
    }


}
