package com.askerlve.ascache.store.impl;

import com.askerlve.ascache.store.ValueHolder;

import java.lang.ref.WeakReference;

/**
 * @author Askerlve
 * @Description: 弱引用
 * @date 2018/5/9上午9:32
 */
public class WeakValueHolder<V> implements ValueHolder<V> {

    private WeakReference<V> v;

    public WeakValueHolder(V value) {
        super();
        if (null == value) {
            return;
        }
        this.v = new WeakReference<>(value);
    }

    /**
     * 使用JDk提供的WeakReference 建立对象的弱引用，在没有强引用存在时，若执行GC，jvm将回收对象，调用WeakReference.get时将返回null
     */
    @Override
    public V value() {
        return this.v.get();
    }
}
