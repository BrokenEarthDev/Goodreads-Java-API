package io.github.brokenearthdev.goodreadsjapi.response;

import io.github.brokenearthdev.goodreadsjapi.internal.MultitagContainer;
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

public class ResponsePath {

    private GoodreadsResponse response;
    private Selector<?> selector;

    private MultitagContainer container = new MultitagContainer();

    public ResponsePath(GoodreadsResponse response, Selector<?> selector) {
        verifySelectorType(selector);
        this.response = response;
        this.selector = selector;
    }

    public ResponsePath(GoodreadsResponse response) {
        this(response, NestedDefaultSelector.DEFAULT);
    }

    public ResponsePath(ResponsePath path, Selector<?> selector) {
        verifySelectorType(selector);
        this.response = path.response;
        this.container = path.container;
        this.selector = selector;
    }

    public ResponsePath(ResponsePath path) {
        this(path, path.selector);
    }

    public ResponsePath appendSubdirectory(String tagName, int index) {
        container.append(tagName, index);
        return this;
    }

    private void verifySelectorType(Selector<?> selector) {
        if (!(selector instanceof NestedSelector) && !(selector instanceof EntitySelector)) {
            throw new RuntimeException("selector should be an instance of " + NestedSelector.class + " or " +
                    EntitySelector.class);
        }
    }

    public ResponsePath appendSubdirectory(String tagName) {
        return appendSubdirectory(tagName, -1);
    }

    public ResponseSection findSection() {
        try {
            if (selector instanceof NestedSelector) {
                NestedSelector selector = (NestedSelector) this.selector;
                Element found = selector.select(container,
                        Utilities.removeNonEssentialXML(response.getDocument().children()));
                Document document = Jsoup.parse(found.outerHtml(), "", Parser.xmlParser());
                return new ResponseSectionImpl(this, document);
            }  else return  ((EntitySelector<?>) selector).select(container,
                Utilities.removeNonEssentialXML(response.getDocument().children()));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exists() {
        return findSection() != null;
    }

    public String toDirectory() {
        StringBuilder builder = new StringBuilder();
        container.keySet().forEach(k -> builder.append(k).append("/"));
        String dir = builder.toString();
        return dir.substring(0, dir.length() - 1);
    }

    public MultitagContainer toMultitagContainer() {
        return container;
    }

    public GoodreadsResponse getResponse() {
        return response;
    }

}
