package io.github.brokenearthdev.goodreadsjapi.response;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import io.github.brokenearthdev.goodreadsjapi.utils.JavaUtils;
import io.github.brokenearthdev.goodreadsjapi.utils.XMLUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.*;

class ResponsePathImpl implements ResponsePath {

    private LinkedHashMap<String, Integer> mapPath;
    private GoodreadsResponse response;

    private String dir;
    private Document contained;

    ResponsePathImpl(LinkedHashMap<String, Integer> mapPath, GoodreadsResponse response) {
        this.mapPath   = mapPath;
        this.response  = response;
        this.dir       = toDir();
        this.contained = getContained();
    }

    private String toDir() {
        StringBuilder builder = new StringBuilder();
        mapPath.keySet().forEach(k -> builder.append(k).append("/"));
        String dir = builder.toString();
        return dir.substring(0, dir.length() - 1);
    }

    private Document getContained() {
        try {
            Document doc = response.getDocument();
            Elements deep = goDeep(XMLUtils.removeNonEssential(doc.children()), mapPath, new LinkedList<>(mapPath.keySet()));
            return Jsoup.parseBodyFragment(deep.outerHtml());
        } catch (Exception e) {
            return null;
        }
    }

    private Element getElementOfType(Elements e, String tagName, int index) {
        Elements elements = new Elements();
        for (Element element : e) {
            if (element.tagName().equals(tagName))
                elements.add(element);
        }
        return elements.get(index);
    }

    private Elements goDeep(Elements children, Map<String, Integer> map,
                            List<String> paths) {
        if (map.size() == 0) {
            return children;
        }
        List<String> newList = paths.subList(1, paths.size());
        Map<String, Integer> newMap = JavaUtils.subMap(map, 1, map.size() - 1);
        Map.Entry<String, Integer> entry = new LinkedList<>(map.entrySet()).get(0);
        Element next = getElementOfType(children, entry.getKey(), entry.getValue());
        return goDeep(next.children(), newMap, newList);
    }


    @Override
    public ResponseSection getResponseSection() {
        return new ResponseSectionImpl(this, contained);
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
        return contained != null;
    }

    @Override
    public GoodreadsResponse getResponse() {
        return response;
    }
}
