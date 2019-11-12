package com.keep.kit.smallpro.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Date: 2018/9/17
 * Time: 下午3:09
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 对象转json
     * 
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转换换成对象
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        T result = null;
        try {
            result = OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * json转换换成集合
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        try {
            List<Map<String, Object>> list = OBJECT_MAPPER.readValue(json, new TypeReference<List<T>>() {});
            for (Map<String, Object> one : list) {
                result.add(map2object(one, clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> parseArray(String json) {
        List<String> result = new ArrayList<String>();
        try {
            List<String> list = OBJECT_MAPPER.readValue(json, new TypeReference<List<String>>() {});
            result.addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T map2object(Map<String, Object> map, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(map, clazz);
    }
}
