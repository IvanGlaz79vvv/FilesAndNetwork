package ru.ivan.parse_JSON_CSV;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.ivan.MySearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
public class MyParceJSON<T> {
    MySearchFiles mySearchFiles = new MySearchFiles();
    List<String> dataAddress = new ArrayList<>();


    public List<Depths> parceMyJson(String path) {
        List<Depths> list = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(getDataFromJSON(path));
            for (int i = 0; i < jsonArray.toArray().length; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String name = jsonObject.get("station_name").toString();
                String depth = jsonObject.get("depth").toString();
//                int depth = Integer.parseInt(jsonObject.get("depth").toString());
                Depths depths = new Depths(name, depth);
                list.add(depths);
            }
        } catch (Exception e) {
            System.out.println("Список адресов пустой или неверный индекс.");
            e.printStackTrace();
        }
        return list;
    }

    public String getDataFromJSON(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(builder::append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

}
