package com.user.service.CacheCURDAPIs.cache;

import com.user.service.CacheCURDAPIs.config.PropConfig;
import com.user.service.CacheCURDAPIs.entities.User;
import org.apache.commons.collections.map.LRUMap;
import org.springframework.stereotype.Component;

@Component
public class CreateCacheMap {
    // LRUMap: A Map implementation with a fixed maximum size which removes the least recently used entry if an entry is added when full.
    // The least recently used algorithm works on the get and put operations only.
    // Iteration of any kind, including setting the value by iteration, does not change the order.
    // Queries such as containsKey and containsValue or access via views also do not change the order.
    private final LRUMap crunchifyCacheMap;

    protected class CrunchifyCacheObject {

        // currentTimeMillis(): Returns the current time in milliseconds.
        // Note that while the unit of time of the return value is a millisecond,
        // the granularity of the value depends on the underlying operating system and may be larger.
        // For example, many operating systems measure time in units of tens of milliseconds.
        public long lastAccessed = System.currentTimeMillis();
        public User value;

        protected CrunchifyCacheObject(User value) {
            this.value = value;
        }
    }

    public CreateCacheMap() {
        PropConfig propConfig = new PropConfig();
        crunchifyCacheMap = new LRUMap(Integer.parseInt(propConfig.getCacheSize1()));
    }

    public void put(String key, User value) {
        synchronized (crunchifyCacheMap) {
            crunchifyCacheMap.put(key, new CrunchifyCacheObject(value));
        }
    }

    public boolean isKeyPresent(String email) {
        return crunchifyCacheMap.containsKey(email);
    }

    public User get(String key) {
        synchronized (crunchifyCacheMap) {
            CrunchifyCacheObject c;
            c = (CrunchifyCacheObject) crunchifyCacheMap.get(key);
            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public boolean remove(String key) {
        if(isKeyPresent(key)) {
            synchronized (crunchifyCacheMap) {
                crunchifyCacheMap.remove(key);
            }
            return true;
        } else
            return false;
    }

    public int size() {
        synchronized (crunchifyCacheMap) {
            return crunchifyCacheMap.size();
        }
    }
}
