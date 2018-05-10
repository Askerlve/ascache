package com.askerlve.ascache.store.impl;

import com.askerlve.ascache.store.ValueHolder;

import java.util.Map;

/**
 * @author Askerlve
 * @Description: LRU cache存贮
 * @date 2018/5/10上午9:01
 */
public class LRUEntry<K,V extends ValueHolder<?>> implements Map.Entry<K,ValueHolder<?>> {

    final K key; // non-null
    ValueHolder<?> v; // non-null

    //双向链表
    private LRUEntry<K, ValueHolder<?>> pre;
    private LRUEntry<K, ValueHolder<?>> next;

    public LRUEntry(K key, V value) {
        super();

        this.key = key;
        this.v = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public ValueHolder<?> getValue() {
        return this.v;
    }

    @Override
    public ValueHolder<?> setValue(ValueHolder<?> value) {
        ValueHolder<?> oldValue = this.v;
        this.v = value;
        return oldValue;
    }

    public LRUEntry<K, ValueHolder<?>> getPre() {
        return pre;
    }

    public void setPre(LRUEntry<K, ValueHolder<?>> pre) {
        this.pre = pre;
    }

    public LRUEntry<K, ValueHolder<?>> getNext() {
        return next;
    }

    public void setNext(LRUEntry<K, ValueHolder<?>> next) {
        this.next = next;
    }
}
