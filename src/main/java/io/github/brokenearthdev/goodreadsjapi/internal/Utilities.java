package io.github.brokenearthdev.goodreadsjapi.internal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Class not intended for public usage
 */
public class Utilities {

    // JAVA UTILS

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

    public static boolean is(Class<?> c1, Class<?> c2) {
        return c1.equals(c2) || is(c1.getSuperclass(), c2);
    }

    // XML UTILS

    private static final String[] nonEssentials = {"#root", "html", "head", "body"};

    public static Elements removeNonEssentialXML(Elements elements) {
        Elements elements1 = new Elements();
        for (Element element : elements) {
            if (nonEssentialXML(element.tagName())) {
                Elements child = removeNonEssentialXML(element.children());
                elements1.addAll(child);
            } else {
                elements1.add(element);
            }
        }
        return elements1;
    }

    public static boolean nonEssentialXML(String tagName) {
        for (String str : nonEssentials) {
            if (str.equals(tagName))
                return true;
        }
        return false;
    }

    // YAML UTILS

    public static Map<String, Object> fromDocument(Document document) {
        Map<String, Object> map = new LinkedHashMap<>();
        Elements elements = document.getAllElements();
        for (Element element : elements) {
            map.put(element.tagName(), fromElementXX(element));
        }
        return map;
    }

    public static Object fromElementXX(Element element) {
        List<Node> children = element.childNodes();
        if (children.size() >= 1) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (Node node : children) {
                map.put(element.tagName(), node);
            }
            return map;
        }
        return element.text();
    }

}
