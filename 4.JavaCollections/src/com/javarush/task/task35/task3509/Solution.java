package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList <T> newArrayList(T... elements) {
        ArrayList <T> arrayList = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            arrayList.add(elements[i]);
        }
        return arrayList;
    }

    public static <T> HashSet <T> newHashSet(T... elements) {
        HashSet<T> hashSet = new HashSet<>();
        for (int i = 0; i < elements.length; i++) {
            hashSet.add(elements[i]);
        }
        return hashSet;
    }

    public static <K, V> HashMap <K, V> newHashMap(List <? extends K> keys, List<? extends V> values) {
        HashMap<K,V> hashMap = new HashMap<>();
        if (keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                hashMap.put(keys.get(i), values.get(i));
            }
        } else throw new IllegalArgumentException();

        return hashMap;
    }
}
