package io.github.brokenearthdev.goodreadsjapi.response;

import io.github.brokenearthdev.goodreadsjapi.utils.XMLUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ResponsePathBuilder {

    private LinkedHashMap<String, Integer> map;
    private GoodreadsResponse response;
    private LinkedList<String> paths;

    public ResponsePathBuilder(LinkedHashMap<String, Integer> map, GoodreadsResponse response) {
        this.map = map;
        this.response = response;
        this.paths = new LinkedList<>(map.keySet());
    }

    public ResponsePathBuilder(GoodreadsResponse response) {
        this(new LinkedHashMap<>(), response);
    }

    public ResponsePathBuilder appendSubdirectory(String str, int index) {
        if (XMLUtils.nonEssential(str)) return this;
        map.put(str, index);
        return this;
    }

    public ResponsePathBuilder appendSubdirectories(String[] strings, int[] indexes) {
        if (strings.length != indexes.length) return this;
        for (int i = 0; i < strings.length; i++) {
            appendSubdirectory(strings[i], indexes[i]);
        }
        return this;
    }

    public ResponsePath build() {
        return new ResponsePathImpl(map, response);
    }

}
