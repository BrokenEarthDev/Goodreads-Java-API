package io.github.brokenearthdev.goodreadsjapi.utils;

import sun.awt.image.ImageWatched;

import java.util.*;

public class JavaUtils {

    public static <K, V> Map<K, V> subMap(Map<K, V> map, int startIndex, int endIndex) {
        if (endIndex + 1 > map.size() || startIndex < 0)
            throw new IndexOutOfBoundsException("Invalid index position");
        Map<K, V> subMap = null;
        if (map instanceof LinkedHashMap)
            subMap = new LinkedHashMap<>();
        else subMap = new HashMap<>();
        LinkedList<Map.Entry<K, V>> entryList = new LinkedList<>(map.entrySet());
        for (int i = startIndex; i <= endIndex; i++) {
            subMap.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }
        return subMap;
    }

}
