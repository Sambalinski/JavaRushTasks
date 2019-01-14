package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        //напишите тут ваш код
        try {
            return softReference.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public AnyObject put(Long key, AnyObject value) {
        AnyObject anyObject;
        try {
            anyObject = cacheMap.get(key).get();
        } catch (NullPointerException e) {
            anyObject = null;
        }
        //строка дана по условию
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));

        try {
            softReference.clear();
        } catch (NullPointerException e) {}
        return anyObject;
    }

    public AnyObject remove(Long key) {
        AnyObject anyObject;
        try {
            anyObject = cacheMap.get(key).get();

            //строка дана по условию
            SoftReference<AnyObject> softReference = cacheMap.remove(key);

            softReference.clear();
        } catch (NullPointerException e) {
            anyObject = null;
        }

        return anyObject;
    }

}