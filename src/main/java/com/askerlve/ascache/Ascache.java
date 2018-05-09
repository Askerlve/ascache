package com.askerlve.ascache;

import com.askerlve.ascache.store.DataStore;
import com.askerlve.ascache.store.StoreAccessException;
import com.askerlve.ascache.store.ValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Askerlve
 * @Description: Ascache
 * @date 2018/5/9上午9:41
 */
public class Ascache<K,V> {

    private final DataStore<K, V> store;
    private static Logger logger = LoggerFactory.getLogger(Ascache.class);

    public Ascache(final DataStore<K, V> dataStore) {
        this.store = dataStore;
    }

    public V get(final K key) {
        try {
            ValueHolder<V> value = this.store.get(key);
            if (null == value) {
                return null;
            }
            return value.value();
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
            return null;
        }
    }

    public void put(final K key, final V value) {
        try {
            this.store.put(key, value);
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
        }
    }

    public V remove(K key) {
        try {
            ValueHolder<V> value = this.store.remove(key);
            return value.value();
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
            return null;
        }
    }

    public void clear() {
        try {
            this.store.clear();
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
        }
    }

}
