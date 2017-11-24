package org.springframework.ext.common.helper;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件描述：Json处理辅助类
 *
 * @author only
 * @since 2014/12/9.
 */
public abstract class JsonHelper {
    /** builder */
    private static final GsonBuilder builder = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date.class, new DateFormatter())
            .disableHtmlEscaping();
    /** json处理器 */
    private static Gson gson = builder.create();

    /**
     * 将object对象转为json字符串
     *
     * @param object 待转json的对象
     * @param <T>    对象object的泛型类型
     * @return json字符串
     */
    public static <T> String toJson(T object) {

        if (StringUtils.isNotEmpty("")) {
        }
        if (object == null) {
            return "null";
        }

        // 默认用gson序列化方式
        return gson.toJson(object);
    }

    /**
     * 将json字符串转为clazz类型的java对象
     *
     * @param json  字符串json数据
     * @param clazz 类
     * @param <T>   泛型
     * @return clazz类型的java对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        // 默认用gson反序列化
        return gson.fromJson(json, clazz);
    }

    /**
     * 将json字符串转为type类型的java对象
     *
     * @param json 字符串json数据
     * @param type 类型
     * @param <T>  泛型
     * @return type类型的java对象
     */
    public static <T> T fromJson(String json, Type type) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        // 默认用gson反序列化
        return gson.fromJson(json, type);
    }

    /**
     * 将json字符串转为Map类型的集合
     *
     * @param json  字符串json数据
     * @param <K,V> 泛型
     * @return Map类型的集合
     */
    public static <K, V> Map<K, V> fromJsonMap(String json) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyMap();
        } else {
            Type type = new TypeToken<Map<K, V>>() {
            }.getType();

            Gson gson = builder.registerTypeAdapter(type,
                    (JsonDeserializer<Map<K, V>>) (jsonElement, typeOf, context) -> {

                        Map<K, V> map = Maps.newHashMap();
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                        for (Map.Entry<String, JsonElement> entry : entrySet) {
                            map.put((K) entry.getKey(), (V) entry.getValue());
                        }
                        return map;
                    })
                    .create();

            return gson.fromJson(json, type);
        }
    }

    /**
     * 将json字符串转为List类型的集合
     *
     * @param json 字符串json数据
     * @param <T>  泛型
     * @return List类型的集合
     */
    public static <T> List<T> fromJsonList(String json) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyList();
        } else {
            return fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }
    }

    // 日志格式化
    private static class DateFormatter implements JsonDeserializer<Date> {
        private static final String[] DATE_FORMATS = new String[]{
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd",
                "yyyy/MM/dd"
        };

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context) throws JsonParseException {
            for (String format : DATE_FORMATS) {
                try {
                    return new SimpleDateFormat(format).parse(jsonElement.getAsString());
                } catch (ParseException e) {
                }
            }
            throw new JsonParseException("Unparseable date:\"" + jsonElement.getAsString() + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
        }
    }
}
