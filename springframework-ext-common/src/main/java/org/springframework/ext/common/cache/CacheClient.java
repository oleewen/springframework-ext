package org.springframework.ext.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 缓存接口
 *
 * @author only 2014-6-25
 */
public interface CacheClient {
    /**
     * 生成缓存的key
     *
     * @param identifies 标识数组
     * @return 缓存的key
     */
    Serializable key(Serializable... identifies);

    /**
     * 刷新缓存：设置从该时刻起，在该时间之前缓存的数据，全部跳过取缓存
     *
     * @return 设置时间是否成功
     */
    boolean refresh();

    /**
     * 如果有缓存则返回；否则运算、缓存、然后返回
     *
     * @param <T>      缓存的对象类型
     * @param key      要获取的数据的key
     * @param callable 运算数据的方法
     * @param expire   缓存失效时间
     * @return 返回缓存对象
     */
    <T> T get(Serializable key, Callable<T> callable, int expire);

    /**
     * 获取数据
     *
     * @param key 要获取的数据的key
     * @return 返回缓存对象
     */
    <T> T get(Serializable key);

    /**
     * 批量获取数据
     *
     * @param keys 要获取的数据的key列表
     * @return 如果成功，返回的数据对象为一个Map<Key, Value>
     */
    <K, V> Map<K, V> mget(List<K> keys);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param key    缓存的key
     * @param value  缓存的value
     * @param expire 数据的有效时间，单位为秒
     * @return 是否缓存成功
     */
    boolean put(Serializable key, Serializable value, int expire);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param key     缓存的key
     * @param value   缓存的value
     * @param expire  数据的有效时间，单位为秒
     * @param version 数据的版本，版本不一致，则更新失败
     * @return 是否缓存成功
     */
    boolean put(Serializable key, Serializable value, int expire, int version);

    /**
     * 删除key对应的数据
     *
     * @param key 数据的key
     * @return 删除缓存是否成功
     */
    boolean delete(Serializable key);

    /**
     * 批量删除，如果全部删除成功，返回成功，否则返回失败
     *
     * @param keys 要删除数据的key列表
     * @return 批量删除是否成功
     */
    boolean mdelete(List<? extends Object> keys);

    /**
     * 失效key对应的数据
     *
     * @param key 数据的key
     * @return 失效缓存是否成功
     */
    boolean invalid(Serializable key);

    /**
     * 批量失效数据，该方法将失效由失效服务器配置的多个实例中当前group下的数据
     *
     * @param keys 要失效的key列表
     * @return
     */
    boolean minvalid(List<? extends Object> keys);

}
