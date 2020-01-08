package com.example.commons.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;

import java.io.IOException;

/**
 * Json utils.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public class JsonUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads the object in JSON.
     *
     * @param value         The value to read.
     * @param typeReference The type reference when reading.
     * @param <T>           The type of object being handled.
     * @return The result object.
     */
    public static <T> T readJson(String value, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(value, typeReference);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * Writes the object in JSON.
     *
     * @param obj The object to write in JSON.
     * @return The result in JSON.
     */
    public static String writeJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * Reads the given object in JSON.
     *
     * @param value The value to read.
     * @param klass The result class.
     * @param <T>   The type of object being read.
     * @return The result object.
     */
    public static <T> T readJson(String value, Class<T> klass) {
        try {
            return objectMapper.readValue(value, klass);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}
