package com.askerlve.ascache.store.impl;

import com.askerlve.ascache.store.DataStore;
import com.askerlve.ascache.store.StoreAccessException;
import com.askerlve.ascache.store.ValueHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Askerlve
 * @Description: LRUDataStore
 * @date 2018/5/10上午9:08
 */
public class LRUDataStore<K,V> implements DataStore<K,V> {

    private final int maxSize;

    private ConcurrentHashMap<K, LRUEntry<K, ValueHolder<?>>> map = new ConcurrentHashMap<>();

    private LRUEntry<K, ValueHolder<?>> first;
    private LRUEntry<K, ValueHolder<?>> last;

    public LRUDataStore(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public ValueHolder<V> get(K key) throws StoreAccessException {
        LRUEntry<K, ValueHolder<?>> entry = this.getEntry(key);
        if (entry == null) {
            return null;
        }
        this.moveToFirst(entry);
        return (ValueHolder<V>) entry.getValue();
    }

    @Override
    public PutStatus put(K key, V value) throws StoreAccessException {
        LRUEntry<K, ValueHolder<?>> entry = this.getEntry(key);
        PutStatus status;
        if (entry == null) {
            if (this.map.size() >= this.maxSize) {
                this.map.remove(this.last.getKey());
                this.removeLast();
            }
            entry = new LRUEntry<>(key, new BasicValueHolder<>(value));
            status = PutStatus.PUT;
        } else {
            entry.setValue(new BasicValueHolder<>(value));
            status = PutStatus.UPDATE;
        }
        this.moveToFirst(entry);
        this.map.put(key, entry);
        return status;
    }

    @Override
    public ValueHolder<V> remove(K key) throws StoreAccessException {
        LRUEntry<K, ValueHolder<?>> entry = this.getEntry(key);
        if (entry != null) {
            if (entry.getPre() != null)
                entry.getPre().setNext(entry.getNext());
            if (entry.getNext() != null)
                entry.getNext().setPre(entry.getPre());
            if (entry == this.first)
                this.first = entry.getNext();
            if (entry == this.last)
                this.last = entry.getPre();
        }
        LRUEntry<K, ValueHolder<?>> oldValue = this.map.remove(key);
        return (ValueHolder<V>) oldValue.getValue();
    }

    @Override
    public void clear() throws StoreAccessException {
        this.map.clear();
        this.first = this.last = null;
    }

    /**
     * 移除最后一个元素
     */
    private void removeLast() {
        if (this.last != null) {
            this.last = this.last.getPre();
            if (this.last == null)
                this.first = null;
            else
                this.last.setNext(null);
        }
    }

    /**
     * 将给定的节点移到首位
     * @param entry
     */
    private void moveToFirst(LRUEntry<K, ValueHolder<?>> entry) {

        if (entry == this.first)
            return;
        if (entry.getPre() != null)
            entry.getPre().setNext(entry.getNext());
        if (entry.getNext() != null)
            entry.getNext().setPre(entry.getPre());
        if (entry == this.last)
            this.last = this.last.getPre();

        if (this.first == null || this.last == null) {
            this.first = this.last = entry;
            return;
        }

        entry.setNext(this.first);
        this.first.setPre(entry);
        this.first = entry;
        entry.setPre(null);
    }

    private LRUEntry<K, ValueHolder<?>> getEntry(K key) {
        return this.map.get(key);
    }
}
