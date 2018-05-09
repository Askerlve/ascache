package com.askerlve.ascache.store.impl;

import com.askerlve.ascache.store.ValueHolder;

/**
 * @author Askerlve
 * @Description: BasicValueHolder
 * @date 2018/5/9上午9:45
 */
public class BasicValueHolder<V> implements ValueHolder<V>{

    private final V refValue;

    public BasicValueHolder(final V value) {
        this.refValue = value;
    }

    @Override
    public V value() {
        return this.refValue;
    }

}
