package com.web.bear.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object obj) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
