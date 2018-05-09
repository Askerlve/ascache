package com.askerlve.ascache.store;

/**
 * @author Askerlve
 * @Description: 数据存储接口
 * @date 2018/5/9上午9:24
 */
public interface DataStore<K,V> {

    /**
     * 获取缓存
     * @param key
     * @return
     * @throws StoreAccessException
     */
    ValueHolder<V> get(K key) throws StoreAccessException;

    /**
     * 放入缓存
     * @param key
     * @param value
     * @return
     * @throws StoreAccessException
     */
    PutStatus put(K key, V value) throws StoreAccessException;

    /**
     * 移除缓存
     * @param key
     * @return
     * @throws StoreAccessException
     */
    ValueHolder<V> remove(K key) throws StoreAccessException;

    /**
     * 清空缓存
     * @throws StoreAccessException
     */
    void clear() throws StoreAccessException;

    enum PutStatus {
        /**
         * New value was put
         */
        PUT,
        /**
         * New value was put and replace old value
         */
        UPDATE,
        /**
         * New value was dropped
         */
        NOOP
    }

}
