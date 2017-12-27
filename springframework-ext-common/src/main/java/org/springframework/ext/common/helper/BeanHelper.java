package org.springframework.ext.common.helper;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.ext.common.exception.ExceptionHelper;
import org.springframework.ext.common.exception.NestedRuntimeException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

/**
 * 扩展Apache的BeanUtil
 * User: only
 * Date: 2014/8/22
 * Time: 21:37
 */
public abstract class BeanHelper {
    /**
     * 拷贝相同名称属性的值, 不同类型的属性会尝试转换
     *
     * @param desc 目标对象
     * @param orig 原始对象
     * @param <T>  对象类型
     * @return 目标对象
     */
    public static <T> T copyProperties(T desc, Object orig) {
        try {
            BeanUtils.copyProperties(orig, desc);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return desc;
    }

    public static Map<String, Object> toMap(Object object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            Map<String, Object> result = Maps.newHashMap();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性，且属性无写方法
                if (!key.equals("class") && property.getWriteMethod() != null && property.getReadMethod() != null) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);

                    if (value != null) {
                        result.put(key, value);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    /**
     * 取实现类的泛型数组
     *
     * @return 泛型数组
     */
    public static Type[] getGenericTypes(Class<?> clazz) {
        if (clazz.getSuperclass().getName().equals(Object.class.getName())) {
            throw new UnsupportedOperationException("this is a sub instance of Object");
        }

        Type type;
        do {
            // 取泛型参数
            type = clazz.getGenericSuperclass();
            clazz = clazz.getSuperclass();
        }
        // 从当前类向上，直到找到泛型类
        while (!(type instanceof ParameterizedType));
        // 取泛型集合
        return ((ParameterizedType) type).getActualTypeArguments();
    }

    /**
     * 新建结果对象，该方法由子类实现
     *
     * @return 结果对象
     */
    public static <T> T instance(Type type) {
        // 用RESULT泛型
        Class<T> clazz;
        if (type instanceof Class) {
            clazz = (Class<T>) type;
        }
        // 泛型
        else {
            clazz = (Class<T>) ((ParameterizedType) type).getRawType();
        }
        // 创建对象
        return instance(clazz);
    }

    /**
     * 创建clazz的实例
     *
     * @param clazz 类型信息
     * @return clazz实例
     */
    public static <T> T instance(Class<T> clazz) {
        T result = null;
        try {
            result = clazz.newInstance();
        } catch (Exception e) {
            throw ExceptionHelper.throwException(e);
        }
        return result;
    }
}
