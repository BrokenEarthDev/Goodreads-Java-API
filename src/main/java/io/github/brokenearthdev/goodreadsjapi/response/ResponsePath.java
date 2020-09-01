package io.github.brokenearthdev.goodreadsjapi.response;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.internal.Container;
import io.github.brokenearthdev.goodreadsjapi.internal.TagIndexContainer;
import io.github.brokenearthdev.goodreadsjapi.internal.Utilities;
import io.github.brokenearthdev.goodreadsjapi.internal.impl.ResponseSectionImpl;
import io.github.brokenearthdev.goodreadsjapi.selector.NestedDefaultSelector;
import io.github.brokenearthdev.goodreadsjapi.selector.NestedSelector;
import io.github.brokenearthdev.goodreadsjapi.selector.Selector;
import io.github.brokenearthdev.goodreadsjapi.selector.entity.EntitySelector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class ResponsePath {

    private TagIndexContainer container;
    private Selector<?> selector;

    private GoodreadsResponse response;

    public ResponsePath(GoodreadsResponse response, Selector<?> selector) {
        check(selector);
        this.container = new TagIndexContainer();
        this.selector = selector;
        this.response = response;
    }

    public ResponsePath(GoodreadsResponse response) {
        this(response, NestedDefaultSelector.NESTED_INDEX_SELECTOR);
    }

    public ResponsePath(ResponsePath path) {
        this(path, path.selector);
    }

    public ResponsePath(ResponsePath path, Selector<?> selector) {
        this.selector = selector;
        this.container = path.container;
        this.response = path.response;
    }

    private void check(Selector<?> selector) {
        if (!(selector instanceof EntitySelector) && !(selector instanceof NestedSelector))
            throw new RuntimeException(selector.getClass() + " isn't instance of " + EntitySelector.class + " or " +
                    NestedSelector.class);
    }

    public ResponsePath append(String tag, int index) {
        container.addElement(tag, index);
        return this;
    }

    public ResponsePath append(String tag) {
        return append(tag, 0);
    }

    public ResponseSection findSection() {
        if (selector instanceof NestedSelector) {
            NestedSelector selector = (NestedSelector) this.selector;
            Element selected = selector.select(container,
                    Utilities.removeNonEssentialXML(response.getDocument().children()));
            Document document = Jsoup.parse(selected.outerHtml(), "", Parser.xmlParser());
            return new ResponseSectionImpl(this, document);
        } else {
            EntitySelector<? extends Entity> selector = (EntitySelector<? extends Entity>) this.selector;
            Elements children = Utilities.removeNonEssentialXML(response.getDocument().children());
            return selector.select(container, children);
        }
    }

    public GoodreadsResponse getResponse() {
        return response;
    }

    public TagIndexContainer getContainer() {
        return container;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        List<TagIndexContainer.Entry> entries = container.getElements();
        entries.forEach(e -> builder.append(e.getTagName()).append(" ").append("(").append(e.getIndex()).append(")")
        .append(" > "));
        String str = builder.toString();
        return str.substring(0, str.length() - 2);
    }

    public Selector<?> getSelector() {
        return selector;
    }

}
