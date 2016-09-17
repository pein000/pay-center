//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.pein.alibaba.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtil  {
    public MapUtil() {
    }

    public static <K, T> T get(Map<K, T> map, Object key) {
        return map != null?map.get(key):null;
    }

    public static <K, V> void put(Map<K, List<V>> map, K key, V value) {
        if(map != null) {
            Object list = (List)map.get(key);
            if(list == null) {
                list = new LinkedList();
                map.put(key, (List<V>) list);
            }

            ((List)list).add(value);
        }

    }

    public static <K, V> List<V> getList(Map<K, List<V>> map, K key) {
        if(map == null) {
            return null;
        } else {
            Object list = (List)map.get(key);
            if(list == null) {
                list = new LinkedList();
                map.put(key, (List<V>) list);
            }

            return (List)list;
        }
    }

    public static <J, K, V> Map<K, V> getSubMap(Map<J, Map<K, V>> map, J key) {
        if(map == null) {
            return null;
        } else {
            Object map1 = (Map)map.get(key);
            if(map1 == null) {
                map1 = new HashMap();
                map.put(key, (Map<K, V>) map1);
            }

            return (Map)map1;
        }
    }

    public static <T> Map<String, T> clone(Map<String, T> map) {
        HashMap remap = new HashMap();
        Iterator i$ = map.keySet().iterator();

        while(i$.hasNext()) {
            String key = (String)i$.next();
            remap.put(key, map.get(key));
        }

        return remap;
    }

    public static Map<String, Object> castMapByObject(Object object) {
        new HashMap();
        String jsonStr = JsonBeanUtil.buildNormalBinder().toJson(object);
        Map remap = (Map)JsonBeanUtil.buildNormalBinder().getJsonToMap(jsonStr, String.class, Object.class);
        return remap;
    }

    public static Map<String, String> castMap(Object object) {
        new HashMap();
        String jsonStr = JsonBeanUtil.buildNormalBinder().toJson(object);
        Map remap = (Map)JsonBeanUtil.buildNormalBinder().getJsonToMap(jsonStr, String.class, String.class);
        return remap;
    }
}
