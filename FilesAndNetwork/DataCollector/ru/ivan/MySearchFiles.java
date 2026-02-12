package ru.ivan;

import java.io.File;

public class MySearchFiles {
    public void readMyFiles(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            recursiveListFiles(file);
        }
    }

    private void recursiveListFiles(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    recursiveListFiles(file); // Рекурсивно спускаемся глубже, если это папка
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".csv") || fileName.endsWith(".json"))
                        System.out.println(file); // Выведем абсолютный путь к файлу
                }
            }
        }
    }
}

/*public class MySearchFiles {
    public void readMyFiles(String path) {
        File file = new File(path);
        File[] files;
        files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println();
            System.out.println(files[i]);
            if (files[i].isDirectory() || !files[i].exists()) {
                File[] tmp = files[i].listFiles();
                recursiveListFiles(files[i]);

                *//*for (File f1 : tmp) {
                    System.out.print(f1 + "\t");
                }*//*
                System.out.println();
            }
        }
    }

    public void recursiveListFiles(File dir) {
        if (dir.isDirectory() || !dir.exists()) {
            for (int i = 0; i < dir.length(); i++) {
                File[] tmp = dir.listFiles();
                for (File f1 : tmp) {
                    System.out.print(f1 + "\t");
                }
                recursiveListFiles(dir);
            }
        }
    }
}*/
