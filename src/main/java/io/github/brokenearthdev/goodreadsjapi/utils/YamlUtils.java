/*
Copyright 2019 BrokenEarthDev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.github.brokenearthdev.goodreadsjapi.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class not intended for public usage
 */
public class YamlUtils {

    public static Map<String, Object> fromDocument(Document document) {
        Map<String, Object> map = new LinkedHashMap<>();
        Elements elements = document.getAllElements();
        for (Element element : elements) {
            map.put(element.tagName(), fromElement(element));
        }
        return map;
    }

    public static Object fromElement(Element element) {
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
