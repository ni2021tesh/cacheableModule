package cacheableModule;

public class TestCacheable {


    public static void main(String[] args) {
        String name = "Nitesh";
        CachedObject<String, String> nCachedObjWrapper = new CachedObject<String, String>("1234", name, 1);
        CacheManager.saveObjectToCache(nCachedObjWrapper);
        Object cachedObject1 = CacheManager.getCachedObject("1234");
        if (cachedObject1 == null) {
            System.out.println("obj not found in cache");
        } else
            System.out.println("Object " + cachedObject1 + " found in cache");

        Employee employee = new Employee("JhaNi", 56892);
        CachedObject<Long, Employee> nCachedObjWrapper2 = new CachedObject<Long, Employee>(123456L, employee, 1);
        CacheManager.saveObjectToCache(nCachedObjWrapper2);
        Object cachedObject2 = CacheManager.getCachedObject(123456L);
        if (cachedObject2 == null) {
            System.out.println("obj not found in cache");
        } else
            System.out.println("Object " + cachedObject2 + " found in cache");


    }


}
