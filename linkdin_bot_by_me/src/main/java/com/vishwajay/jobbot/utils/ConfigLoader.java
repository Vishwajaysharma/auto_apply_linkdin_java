package com.vishwajay.jobbot.utils;
/*
 * ConfigLoader.java - Loads user config from JSON file
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigLoader {
    public static Map<String, String> load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Map.class);
    }
}