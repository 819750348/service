package com.snake.web.boot.utils;

import java.util.HashMap;
import java.util.Map;

public class HashMaps<K, V> extends HashMap<K, V> {

    public static <K, V> Map<K, V> asHashMap(K[] keys, V[] values) {
        assert null != keys && null != values && keys.length == values.length;
        Map map = new HashMap<K, V>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public static <K, V> HashMaps<K, V> build() {
        return new HashMaps<K, V>();
    }

    public HashMaps<K, V> as(K key, V value) {
        this.put(key, value);
        return this;
    }

}