package com.web.bear.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;

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

    public static <T> T jsonToObject(String json, TypeReference typeReference) {

        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
