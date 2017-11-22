package org.springframework.ext.common.helper;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 文件描述：Json处理辅助类
 *
 * @author only
 *         Date 2014/12/9.
 */
public abstract class JsonHelper {
    /** json处理器 */
    private static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(DateFormat.FULL)
            .disableHtmlEscaping().create();

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

            Gson gson = new GsonBuilder()
                    /**
                     * 重写map的反序列化：整数默认反序列化为Long
                     */
                    .registerTypeAdapter(type, new MapTypeAdapter()).create();

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

    // 复写数字类型默认反序列化为double的
    public static class MapTypeAdapter extends TypeAdapter<Object> {

        @Override
        public Object read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    List<Object> list = Lists.newArrayList();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(read(in));
                    }
                    in.endArray();
                    return list;

                case BEGIN_OBJECT:
                    Map<String, Object> map = new LinkedTreeMap<String, Object>();
                    in.beginObject();
                    while (in.hasNext()) {
                        map.put(in.nextName(), read(in));
                    }
                    in.endObject();
                    return map;

                case STRING:
                    return in.nextString();

                case NUMBER:
                    /**
                     * 改写数字的处理逻辑，将数字值分为整型与浮点型。
                     */
                    double dbNum = in.nextDouble();

                    // 数字超过long的最大值，返回浮点类型
                    if (dbNum > Long.MAX_VALUE) {
                        return dbNum;
                    }

                    // 判断数字是否为整数值
                    long lngNum = (long) dbNum;
                    if (dbNum == lngNum) {
                        return lngNum;
                    } else {
                        return dbNum;
                    }

                case BOOLEAN:
                    return in.nextBoolean();

                case NULL:
                    in.nextNull();
                    return null;

                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            // 序列化无需实现
        }

    }
}
