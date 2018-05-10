package com.askerlve.ascache;

import com.askerlve.ascache.bean.User;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * @author Askerlve
 * @Description: AsCache107Test
 * @date 2018/5/10上午9:49
 */
public class AsCache107Test {

    @Test
    public void test01() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager manager = cachingProvider.getCacheManager();
        Cache<String, User> cache = manager.<String, User, Configuration<String, User>> createCache("Test", new MutableConfiguration<>());
        String key = "leo";
        User user = new User();
        user.setName("leo");
        cache.put(key, user);
        System.out.println("Hello " + cache.get(key).getName());

    }

}
