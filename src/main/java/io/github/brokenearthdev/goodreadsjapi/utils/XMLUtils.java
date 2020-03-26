package io.github.brokenearthdev.goodreadsjapi.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XMLUtils {

    public static final String[] nonEssentials = {"#root", "html", "head", "body"};

    public static Elements removeNonEssential(Elements elements) {
        Elements elements1 = new Elements();
        for (Element element : elements) {
            if (nonEssential(element.tagName())) {
                Elements child = removeNonEssential(element.children());
                elements1.addAll(child);
            } else {
                elements1.add(element);
            }
        }
        return elements1;
    }

    public static boolean nonEssential(String tagName) {
        for (String str : nonEssentials) {
            if (str.equals(tagName))
                return true;
        }
        return false;
    }

}
