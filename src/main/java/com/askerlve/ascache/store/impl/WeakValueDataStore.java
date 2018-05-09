package com.askerlve.ascache.store.impl;

import com.askerlve.ascache.store.DataStore;
import com.askerlve.ascache.store.StoreAccessException;
import com.askerlve.ascache.store.ValueHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Askerlve
 * @Description: 弱引用方式实现存贮
 * @date 2018/5/9上午9:29
 */
public class WeakValueDataStore<K, V> implements DataStore<K, V> {

    private ConcurrentHashMap<K, ValueHolder<V>> map = new ConcurrentHashMap<K, ValueHolder<V>>();

    @Override
    public ValueHolder<V> get(K key) throws StoreAccessException {
        return this.map.get(key);
    }

    @Override
    public PutStatus put(K key, V value) throws StoreAccessException {
        ValueHolder<V> v = new WeakValueHolder<>(value);
        this.map.put(key, v);
        return PutStatus.PUT;
    }

    @Override
    public ValueHolder<V> remove(K key) throws StoreAccessException {
        return this.map.remove(key);
    }

    @Override
    public void clear() throws StoreAccessException {
        this.map.clear();
    }
}
