package org.springframework.ext.common.cache;

import com.google.common.base.Throwables;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ext.common.setting.Context;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 文件描述：Guava缓存(本地)客户端
 *
 * @author only
 *         Date 2015/7/21.
 */
public class GuavaCacheClient implements CacheClient {
    /** 日志对象 */
    private static Logger logger = LoggerFactory.getLogger(GuavaCacheClient.class);
    /** 最大缓存记录数 */
    private int maxSize = 1000000;
    /** 默认缓存失效时间 */
    private long expire = 300;
    /** Guava缓存实现 */
    private Cache<Serializable, Serializable> cache;

    /**
     * 初始化
     */
    public void init() {
        if (cache == null) {
            cache = CacheBuilder.newBuilder().maximumSize(maxSize).expireAfterAccess(expire, TimeUnit.SECONDS).weakValues().build();
        }
    }

    @Override
    public Serializable key(Serializable... identifies) {
        StringBuilder sb = new StringBuilder();
        if (identifies != null && identifies.length > 0) {
            for (Serializable each : identifies) {
                sb.append(each);
                sb.append(":");
            }
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public boolean refresh() {
        clear();
        return true;
    }

    /**
     * 如果有缓存则返回；否则运算、缓存、然后返回
     *
     * @param <T>      返回对象类型
     * @param key      要获取的数据的key
     * @param callable 运算数据的方法
     * @param expire   缓存失效时间：该参数在Guava实现中不支持
     * @return 返回缓存对象
     */
    @Override
    public <T> T get(Serializable key, final Callable<T> callable, int expire) {
        // 不需要缓存，清理该key
        if (Context.nocache()) {
            cache.invalidate(key);
        }

        Serializable result;
        try {
            result = cache.get(key, (Callable<? extends Serializable>) callable);

            if (result == null || Context.debug() || logger.isInfoEnabled()) {
                logger.warn(String.format("get\01k\02%s\01c\02%s\01e\02%s", key, callable, expire));
            }
        } catch (Exception e) {
            logger.error(String.format("get\01k\02%s\01c\02%s\01exception\02%s", key, callable, Throwables.getRootCause(e).getMessage()));

            throw Throwables.propagate(e);
        }

        if (result != null) {
            try {
                return (T) result;
            } catch (Exception e) {
                // 类型不一致，失效
                invalid(key);

                logger.error(String.format("get\01k\02%s\01c\02%s\01exception\02%s", key, callable, Throwables.getRootCause(e).getMessage()));
            }
        }
        return null;
    }

    @Override
    public <T> T get(Serializable key) {
        if (!Context.nocache()) {
            try {
                T result = (T) cache.getIfPresent(key);
                if (logger.isInfoEnabled()) {
                    logger.info(String.format("get\01k\02%s\01r\02%s", key, result));
                }
                return result;
            } catch (Exception e) {
                logger.error(String.format("get\01k\02%s", key), e);
            }
        }
        return null;
    }

    @Override
    public <K, V> Map<K, V> mget(List<K> keys) {
        if (!Context.nocache()) {
            try {
                ImmutableMap<K, V> result = (ImmutableMap<K, V>) cache.getAllPresent(keys);
                if (logger.isInfoEnabled()) {
                    logger.info(String.format("mget\01k\02%s\01r\02%s", keys, result));
                }
                // 取缓存成功或部分成功
                return result;
            } catch (Exception e) {
                logger.error(String.format("get\01ks\02%s", keys), e);
            }
        }
        return Collections.EMPTY_MAP;
    }

    @Override
    public boolean put(Serializable key, Serializable value, int expire) {
        return put(key, value, expire, 0);
    }

    @Override
    public boolean put(Serializable key, Serializable value, int expire, int version) {
        cache.put(key, value);
        if (logger.isInfoEnabled()) {
            logger.info(String.format("put\01k\02%s\01v\02%s", key, value));
        }
        return true;
    }

    @Override
    public boolean delete(Serializable key) {
        cache.invalidate(key);
        if (logger.isInfoEnabled()) {
            logger.info(String.format("delete\01k\02%s", key));
        }
        return true;
    }

    @Override
    public boolean mdelete(List<? extends Object> keys) {
        cache.invalidateAll(keys);
        if (logger.isInfoEnabled()) {
            logger.info(String.format("mdelete\01k\02%s", keys));
        }
        return true;
    }

    @Override
    public boolean invalid(Serializable key) {
        cache.invalidate(key);
        if (logger.isInfoEnabled()) {
            logger.info(String.format("delete\01k\02%s", key));
        }
        return true;
    }

    @Override
    public boolean minvalid(List<? extends Object> keys) {
        cache.invalidateAll(keys);
        if (logger.isInfoEnabled()) {
            logger.info(String.format("mdelete\01k\02%s", keys));
        }
        return true;
    }

    public void clear() {
        cache.cleanUp();
    }

    public long size() {
        return cache.size();
    }

    public CacheStats status() {
        return cache.stats();
    }

    public void resetCache(Cache<Serializable, Serializable> cache) {
        this.cache = cache;
    }
}
