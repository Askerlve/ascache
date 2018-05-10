package com.askerlve.ascache;

import com.askerlve.ascache.bean.User;
import com.askerlve.ascache.store.impl.BasicDataStore;
import com.askerlve.ascache.store.impl.LRUDataStore;
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

    @Test
    public void TestLRU() {
        Ascache<String, User> cache = new Ascache<String, User>(new LRUDataStore<>(2));
        String key = "leo";
        User user = new User();
        user.setName("leo");

        String key1 = "liu";
        User user1 = new User();
        user1.setName("liu");

        String key2 = "robin";
        User user2 = new User();
        user2.setName("robin");

        cache.put(key, user);
        cache.put(key1, user1);
        cache.get(key);
        cache.put(key2, user2);

        if (cache.get(key) != null) {
            System.out.println("Hello " + cache.get(key).getName());
        }
        if (cache.get(key1) != null) {
            System.out.println("Hello " + cache.get(key1).getName());
        }
        if (cache.get(key2) != null) {
            System.out.println("Hello " + cache.get(key2).getName());
        }
    }

}
