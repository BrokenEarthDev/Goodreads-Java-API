package io.github.brokenearthdev.goodreadsjapi.selector;

import io.github.brokenearthdev.goodreadsjapi.internal.Container;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class NestedSelector implements Selector<Element> {

    /**
     * Selects the Elements in the path contained in the {@link io.github.brokenearthdev.goodreadsjapi.internal.Container}. Depending
     * on the type of {@link Selector}s, there are different ways elements can be retrieved.
     *
     * @param container The container
     * @param children  The elements that will be scanned.
     * @return The elements that are nested within the children passed in
     */
    @Override
    public abstract Element select(Container<?> container, Elements children);

    public Element getElementOfType(Elements e, String tagName, int index) {
        return getElementsOfType(e, tagName).get(index);
    }

    public Elements getElementsOfType(Elements e, String tagName) {
        Elements elements = new Elements();
        for (Element element : e) {
            if (element.tagName().equalsIgnoreCase(tagName))
                elements.add(element);
        }
        return elements;
    }

}
