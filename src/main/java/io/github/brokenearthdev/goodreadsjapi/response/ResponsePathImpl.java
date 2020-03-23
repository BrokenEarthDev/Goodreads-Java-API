package io.github.brokenearthdev.goodreadsjapi.response;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import io.github.brokenearthdev.goodreadsjapi.utils.JavaUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class ResponsePathImpl implements ResponsePath {

    private LinkedHashMap<String, Integer> mapPath;
    private GoodreadsResponse response;

    private String dir;
    private Document containing;

    public ResponsePathImpl(LinkedHashMap<String, Integer> mapPath, GoodreadsResponse response) {
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

    public Document getContaining() {
        Document doc = response.getDocument();
        Map.Entry<String, Integer> first = new LinkedList<>(mapPath.entrySet()).get(0);
        System.out.println(first.getValue());
        System.out.println(getElementOfType(doc.children(), first.getKey(), first.getValue()));
        Elements deep = goDeep(doc.children(), pointer(first.getKey(),
                getElementOfType(doc.children(), first.getKey(), first.getValue()).children()), mapPath,
                new LinkedList<>(mapPath.keySet()));
        Document document = Jsoup.parse(deep.html());
        return doc;
    }

    private Element getElementOfType(Elements e, String tagName, int index) {
        Elements elements = new Elements();
        for (Element element : e) {
            if (element.tagName().equals(tagName))
                elements.add(element);
        }
        return elements.get(index);
    }

    public Elements goDeep(Elements children, LinkedList<Element> elements, Map<String, Integer> map,
                                       LinkedList<String> paths) {
        if (map.size() == 0) {
            return children;
        }
        Map.Entry<String, Integer> entry = new LinkedList<>(map.entrySet()).get(0);
        if (map.size() == 1) {
            LinkedList<Element> e = pointer(elements.get(0).tagName(), elements.get(0).children());
            return e.get(entry.getValue()).children();
        }
        LinkedList<Element> newElements = pointer(entry.getKey(), elements.get(0).children());
        LinkedList<String> subList = new LinkedList<>(paths.subList(1, paths.size()));
        Map<String, Integer> subMap = JavaUtils.subMap(map, 1, map.size());
        return goDeep(elements.get(entry.getValue()).children(), newElements, subMap, subList);
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
