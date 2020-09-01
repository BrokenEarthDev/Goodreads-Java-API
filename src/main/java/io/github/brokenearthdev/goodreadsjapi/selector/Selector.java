package io.github.brokenearthdev.goodreadsjapi.selector;

import io.github.brokenearthdev.goodreadsjapi.internal.Container;
import org.jsoup.select.Elements;

/**
 * A selector selects {@link T} in a group of {@link Elements}. There
 * are different ways it could be selected depending on the subclass.
 *
 * @param <T> The thing that will be selected given a list of elements
 */
public interface Selector<T> {

    /**
     * Selects the {@link T}s in the path contained in the {@link io.github.brokenearthdev.goodreadsjapi.internal.Container}. Depending
     * on the type of {@link Selector}s, there are different ways {@link T}s can be retrieved.
     *
     * @param container The container
     * @param children The elements that will be scanned.
     * @return The elements that are nested within the children passed in
     */
    T select(Container<?> container, Elements children);

}
