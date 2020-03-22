package io.github.brokenearthdev.goodreadsjapi.response;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ResponsePathImpl implements ResponsePath {

    private LinkedHashMap<String, Integer> mapPath;
    private GoodreadsResponse response;

    private String dir;
    private Document containing;

    ResponsePathImpl(LinkedHashMap<String, Integer> mapPath, GoodreadsResponse response) {
        this.mapPath  = mapPath;
        this.response = response;
        this.dir      = toDir();
    }

    private String toDir() {
        StringBuilder builder = new StringBuilder();
        mapPath.keySet().forEach(k -> builder.append(k).append("/"));
        String dir = builder.toString();
        return dir.substring(0, dir.length() - 1);
    }

    private Document getContaining() {
        Document doc = response.getDocument();
        LinkedList<String> mapKeys = new LinkedList<>(mapPath.keySet());
        LinkedList<Element> first = pointer(mapKeys.get(0), doc.children());
        for (int i = 1; i < mapKeys.size(); i++) {
            List<Element> list = pointer(mapKeys.get(i), first.get(i).children());
            Element element = list.get(mapPath.get(mapKeys.get(i)));
            //todo
        }

        return null;
    }

    private LinkedList<Element> pointer(String tagName, Elements children) {
        LinkedList<Element> elements = new LinkedList<>();
        children.forEach(e -> {
            if (e.tagName().equals(tagName))
                elements.add(e);
        });
        return elements;
    }

    @Override
    public ResponseSection getResponseSection() {
        return null;
    }

    @Override
    public String getDirectory() {
        return dir;
    }

    @Override
    public LinkedHashMap<String, Integer> toMapPath() {
        return mapPath;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public GoodreadsResponse getResponse() {
        return response;
    }
}
