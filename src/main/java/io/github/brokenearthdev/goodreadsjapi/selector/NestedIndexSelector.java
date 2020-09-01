package io.github.brokenearthdev.goodreadsjapi.selector;

import io.github.brokenearthdev.goodreadsjapi.internal.Container;
import io.github.brokenearthdev.goodreadsjapi.internal.TagIndexContainer;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NestedIndexSelector extends NestedSelector {

    public static NestedIndexSelector NESTED_INDEX_SELECTOR = new NestedIndexSelector();

    @Override
    public Element select(Container<?> container, Elements children) {
        if (!(container instanceof TagIndexContainer)) return null;
        TagIndexContainer tagIndexContainer = (TagIndexContainer) container;
        if (container.size() == 1) {
            return getElementOfType(children, tagIndexContainer.getTagNames().get(0),
                    tagIndexContainer.getIndexes().get(0));
        }
        TagIndexContainer newContainer = (TagIndexContainer) tagIndexContainer.subContainer(1, tagIndexContainer.size());
        TagIndexContainer.Entry entry = tagIndexContainer.getElements().get(0);
        Element next = getElementOfType(children, entry.getTagName(), entry.getIndex());
        return select(newContainer, next.children().size() != 0 ? next.children() : new Elements());
    }
}
