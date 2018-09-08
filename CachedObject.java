package cacheableModule;

import java.util.Calendar;
import java.util.Date;

public class CachedObject<K, T> implements Cacheable<K, T> {

    private T cachedObject;

    private K cachedIdentifier;

    private Date cachedObjectTime2Live;

    public CachedObject(K cachedIdentifier, T cachedObject, int timeToLive) {
        this.cachedObject = cachedObject;
        this.cachedIdentifier = cachedIdentifier;

        if (timeToLive != 0) {
            cachedObjectTime2Live = new Date();
            Calendar instance = Calendar.getInstance();
            instance.setTime(cachedObjectTime2Live);
            instance.add(Calendar.MINUTE, timeToLive);
            cachedObjectTime2Live = instance.getTime();
        }
    }

    @Override
    public T getCachedObject() {
        return cachedObject;
    }

    @Override
    public K getCachedIdentifier() {
        return cachedIdentifier;
    }

    @Override
    public boolean isExpired() {
        if (cachedObjectTime2Live != null) {
            if (cachedObjectTime2Live.before(new Date())) {
                System.out.println(cachedObject + " is expired :: expiry_time : " + cachedObjectTime2Live.toString() + " :: current_time : " + new Date());
                return true;
            } else {
                System.out.println(cachedObject + " is not expired :: expiry_time : " + cachedObjectTime2Live.toString() + " :: current_time : " + new Date());
                return false;
            }
        }
        return false;
    }
}
