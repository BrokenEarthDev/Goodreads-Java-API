package io.github.brokenearthdev.goodreadsjapi.selector.entity;

import io.github.brokenearthdev.goodreadsjapi.adapters.BookEntityAdapter;
import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.internal.Container;
import io.github.brokenearthdev.goodreadsjapi.internal.TagIndexContainer;
import io.github.brokenearthdev.goodreadsjapi.response.ResponsePath;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;
import io.github.brokenearthdev.goodreadsjapi.selector.NestedDefaultSelector;
import org.jsoup.select.Elements;

public class BookSelector extends EntitySelector<Book> {

    private final BookEntityAdapter TYPE_ADAPTER = new BookEntityAdapter();

    /**
     * Creates a new instance of the object. The details of the {@link Book} object
     * should be present within the {@link ResponseSection}
     *
     * @param section The {@link ResponseSection}
     * @return A new object of type {@link Book}
     */
    @Override
    public Book newInstance(ResponseSection section) {
        try {
            return TYPE_ADAPTER.convert(section);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects the {@link Book} in the path contained in the {@link Container}. Depending
     * on the type of {@link io.github.brokenearthdev.goodreadsjapi.selector.Selector}s, there are
     * different ways {@link Book}s can be retrieved.
     *
     * @param container The container
     * @param children  The elements that will be scanned.
     * @return The elements that are nested within the children passed in
     */
    @Override
    public Book select(Container<?> container, Elements children) {
        if (!(container instanceof TagIndexContainer)) return null;
        NestedDefaultSelector selector = NestedDefaultSelector.DEFAULT;

        // pseudo response path
        ResponsePath pseudo = createPseudoPath(container, children, selector);

        return newInstance(pseudo.findSection());
    }
}
