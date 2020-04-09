package io.github.brokenearthdev.goodreadsjapi.selector;

import io.github.brokenearthdev.goodreadsjapi.internal.MultitagContainer;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;

public class NestedIndexSelector extends NestedSelector {

    public static NestedIndexSelector NESTED_INDEX_SELECTOR = new NestedIndexSelector();

    @Override
    public Element select(MultitagContainer container, Elements children) {
        if (container.size() == 0) {
            return children.get(children.size() - 1);
        }
        MultitagContainer newContainer = container.subContainer(1, container.size());
        MultitagContainer.Entry entry = new LinkedList<>(container.getEntries()).get(0);
        Element next = getElementOfType(children, entry.getTagName(), entry.getIndex());
        return select(newContainer, next.children().size() != 0 ? next.children() : new Elements(next));
    }

}
