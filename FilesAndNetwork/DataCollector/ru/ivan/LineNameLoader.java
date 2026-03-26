package ru.ivan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class LineNameLoader {
    // Используем ConcurrentHashMap для безопасности в многопоточной среде
    private static final Map<String, String> LINE_NAME_MAPPING = new ConcurrentHashMap<>();

    // Статический блок выполняется один раз при загрузке класса
    static {
        loadMappingFromFile();
    }



    private static void loadMappingFromFile() {
        try (InputStream input = LineNameLoader.class.getClassLoader()
                .getResourceAsStream("lines.properties")) {

            if (input == null) {
                System.err.println("Файл lines.properties не найден!");
                return;
            }

            Properties prop = new Properties();
            prop.load(input); // Загружаем свойства из файла

            // Переносим все данные из Properties в нашу Map
            prop.forEach((key, value) -> {
                // key и value приходят как Object, приводим к String
                LINE_NAME_MAPPING.put(key.toString().trim(), value.toString().trim());
            });

            System.out.println("Загружено " + LINE_NAME_MAPPING.size() + " соответствий из файла.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String normalizeLineName(String input) {
        if (input == null) {
            return null;
        }
        // Используем тот же метод getOrDefault, что и раньше
        return LINE_NAME_MAPPING.getOrDefault(input.trim(), input);
    }
}
