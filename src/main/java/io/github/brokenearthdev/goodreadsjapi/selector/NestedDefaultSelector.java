package io.github.brokenearthdev.goodreadsjapi.selector;

import io.github.brokenearthdev.goodreadsjapi.internal.MultitagContainer;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class NestedDefaultSelector extends NestedIndexSelector {

    public static NestedDefaultSelector DEFAULT = new NestedDefaultSelector();

    @Override
    public Element select(MultitagContainer container, Elements children) {
        List<Integer> newIndexesList = container.getIndexes();
        for (int i = 0; i < container.getIndexes().size(); i++) {
            if (container.getIndexes().get(i) == -1)
                newIndexesList.set(i, 0);
        }
        MultitagContainer newContainer = new MultitagContainer(container.getTagNames(), newIndexesList);
        return super.select(newContainer, children);
    }

}
