package io.github.brokenearthdev.goodreadsjapi.selector;

import io.github.brokenearthdev.goodreadsjapi.internal.Container;
import io.github.brokenearthdev.goodreadsjapi.internal.TagIndexContainer;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class NestedDefaultSelector extends NestedIndexSelector {

    public static NestedDefaultSelector DEFAULT = new NestedDefaultSelector();

    @Override
    public Element select(Container<?> container, Elements children) {
        if (!(container instanceof TagIndexContainer)) return null;
        TagIndexContainer tagIndexContainer = (TagIndexContainer) container;
        List<Integer> indexesList = tagIndexContainer.getIndexes();
        List<Integer> newIndexesList = new LinkedList<>();
        for (int i = 0; i < indexesList.size(); i++) {
            if (tagIndexContainer.getIndexes().get(i) == -1)
                newIndexesList.add(0);
            else newIndexesList.add(indexesList.get(i));
        }
        TagIndexContainer newContainer = new TagIndexContainer(tagIndexContainer.getTagNames(), newIndexesList);
        return super.select(newContainer, children);
    }

}
