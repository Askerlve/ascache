package com.askerlve.ascache;

import com.askerlve.ascache.bean.User;
import com.askerlve.ascache.store.impl.BasicDataStore;
import com.askerlve.ascache.store.impl.WeakValueDataStore;
import org.junit.Test;

/**
 * @author Askerlve
 * @Description: 测试用例
 * @date 2018/5/9上午9:43
 */
public class AscacheTest {

    @Test
    public void TestHelloWorld() {
        Ascache<String, String> cache = new Ascache<String, String>(new BasicDataStore<>());
        String key = "Hello";
        cache.put(key, "World!");
        System.out.println(key + " " + cache.get(key));
    }

    @Test
    public void TestWeakValue() throws InterruptedException {
        Ascache<String, User> cache = new Ascache<>(new WeakValueDataStore<>());
        String key = "leo";
        User user = new User();
        user.setName("leo");
        cache.put(key, user);
        //help gc
        user = null;
        System.out.println("Hello " + cache.get(key).getName());
        System.gc();
        Thread.sleep(1000);
        System.out.println("Hello " + cache.get(key));
    }

}
