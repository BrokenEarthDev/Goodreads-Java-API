package io.github.brokenearthdev.goodreadsjapi.selector.entity;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.internal.Container;
import io.github.brokenearthdev.goodreadsjapi.response.*;
import io.github.brokenearthdev.goodreadsjapi.selector.Selector;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 * Selects an {@link Entity} in an XML file and then converts it to an
 * entity of type {@link T}
 *
 * @param <T> An entity type
 */
public abstract class EntitySelector<T extends Entity> implements Selector<ResponseSection> {

    /**
     * Creates a new instance of the object. The details of the {@link Entity} object
     * should be present within the {@link ResponseSection}
     *
     * @param section The {@link ResponseSection}
     * @return A new object of type {@link T}
     */
    public abstract T newInstance(ResponseSection section);

    protected ResponsePath createPseudoPath(Container<?> container, Elements children, Selector<?> selector) {
        GoodreadsResponse response = new BuiltResponse(null, Jsoup.parse(children.outerHtml(), "",
                Parser.xmlParser()), null);
        return new ResponsePath(response, selector);
    }

}
