package com.learning.spring.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class JsonReader {
    public JSONArray readJson(String url) throws IOException {
        JSONArray result = null;
        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String someText = Read(bufferedReader);
            result = new JSONArray(someText);
        }
        return result;
    }

    public String Read(Reader re) throws IOException {
        StringBuilder str = new StringBuilder();
        int temp;
        do {
            temp = re.read();
            str.append((char) temp);
        } while (temp != -1);

        return str.toString();
    }
}
