package com.somecompany.famousquotes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.http.MediaType;

import javax.persistence.EntityManager;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    public static String toJsonString(Object jsonObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        var module = new SimpleModule();
        objectMapper.registerModule(module);

        return objectMapper.writeValueAsString(jsonObject);
    }

    public static void persistAll(EntityManager entityManager, Object... objects) {
        Arrays.stream(objects).forEachOrdered(entityManager::persist);
    }
}
