package org.springframework.ext.common.cache;

import java.io.Serializable;

/**
 * 文件描述：分布式缓存接口
 *
 * @author only
 *         Date 2015/7/21.
 */
public interface DistributedCacheClient extends CacheClient {

    /**
     * 刷新缓存：设置刷新的开始时间，设置了该时间后，在该时间之前缓存的数据，全部跳过取缓存
     *
     * @param freshTime 刷新开始时间
     * @return 设置时间是否成功
     */
    boolean refresh(long freshTime);

    /**
     * 刷新缓存：设置刷新的开始时间，设置了该时间后，在该时间之前缓存的数据，全部跳过取缓存
     *
     * @param freshTime 刷新开始时间，系统毫秒数
     * @param expire    缓存时间，为0表示不失效（强烈不推荐）
     * @return 设置时间是否成功
     */
    boolean refresh(long freshTime, int expire);

    /**
     * 隐藏key对应的数据
     *
     * @param key 数据的key
     * @return 隐藏缓存是否成功
     */
    boolean hide(Serializable key);

    /**
     * 将key对应的数据加上value，如果key对应的数据不存在，则新增，并将值设置为defaultValue
     * 如果key对应的数据不是int型，则返回失败
     *
     * @param key          数据的key
     * @param value        要加的值
     * @param defaultValue 不存在时的默认值
     * @param expire       数据的有效时间，单位为秒
     * @return 更新后的值
     */
    Integer incr(Serializable key, int value, int defaultValue, int expire);

    /**
     * 将key对应的数据加上value，如果key对应的数据不存在，则新增，并将值设置为defaultValue
     * 如果key对应的数据不是int型，则返回失败
     *
     * @param key          数据的key
     * @param value        要加的值
     * @param defaultValue 不存在时的默认值
     * @param expire       数据的有效时间，单位为秒
     * @param lowBound 下界值
     * @param upperBound 上界值
     * @return 更新后的值
     */
    Integer incr(Serializable key, int value, int defaultValue, int expire, int lowBound, int upperBound);

    /**
     * 将key对应的数据减去value，如果key对应的数据不存在，则新增，并将值设置为defaultValue
     * 如果key对应的数据不是int型，则返回失败
     *
     * @param key          数据的key
     * @param value        要减去的值
     * @param defaultValue 不存在时的默认值
     * @param expire       数据的有效时间，单位为秒
     * @return 更新后的值
     */
    Integer decr(Serializable key, int value, int defaultValue, int expire);

    /**
     * 将key对应的计数设置成count，忽略key原来是否存在以及是否是计数类型。
     *
     * @param key   数据的key
     * @param count 要设置的值
     */
    boolean setCount(Serializable key, int count);

    /**
     * 将key对应的计数设置成count，忽略key原来是否存在以及是否是计数类型。
     *
     * @param key     数据的key
     * @param count   要设置的值
     * @param version 版本，不关心并发，传入0
     * @param expire  过期时间，不使用传入0
     */
    boolean setCount(Serializable key, int count, int version, int expire);

    /**
     * 锁住一个key，不再允许更新, 允许读和删除。
     *
     * @param key 数据的key
     * @return 如果数据不存在，返回不存在；如果数据存在但已经被lock，返回lock已经存在的错误码；
     * 否则成功。
     */
    boolean lock(Serializable key);

    /**
     * 解锁一个key。
     *
     * @param key 数据的key
     * @return 如果数据不存在，返回不存在；如果数据存在但未被lock，返回lock不存在的错误码；
     * 否则成功。
     */
    boolean unlock(Serializable key);
}
