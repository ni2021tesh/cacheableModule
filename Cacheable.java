package cacheableModule;

public interface Cacheable<K, T> {

    public T getCachedObject();

    public K getCachedIdentifier();

    public boolean isExpired();

}
