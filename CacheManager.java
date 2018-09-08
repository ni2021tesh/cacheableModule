package cacheableModule;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {

    private static final ConcurrentHashMap<Object, Object> CONCURRENT_HASH_MAP = new ConcurrentHashMap<Object, Object>();
    private static final int TIME_2_SLEEP = 5000;


    static {
        try {
            Thread cacheCleanerThread = new Thread(CacheManager::run);
            cacheCleanerThread.setName("CACHE_CLEANER");
            cacheCleanerThread.setPriority(Thread.MIN_PRIORITY);
            cacheCleanerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void run() {
        while (true) {
            System.out.println("Scanning for Expired cache");
            Set<Object> keySet = CONCURRENT_HASH_MAP.keySet();
            for (Object key : keySet) {
                CachedObject cachedObj = (CachedObject) CONCURRENT_HASH_MAP.get(key);
                if (cachedObj.isExpired()) {
                    CONCURRENT_HASH_MAP.remove(key);
                    System.out.println(cachedObj + " expired and is removed from the cache");
                }
            }
            try {
                Thread.sleep(TIME_2_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void saveObjectToCache(CachedObject objectToSave) {
        System.out.println("Saving " + objectToSave.getCachedObject() + " in cache");
        CONCURRENT_HASH_MAP.put(objectToSave.getCachedIdentifier(), objectToSave);
    }

    public static <R> Object getCachedObject(R identifier) {
        CachedObject cachedObject = (CachedObject) CONCURRENT_HASH_MAP.get(identifier);

        if (cachedObject == null)
            return null;

        if (cachedObject.isExpired())
            return null;

        return cachedObject.getCachedObject();

    }
}
