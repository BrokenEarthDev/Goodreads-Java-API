package io.github.brokenearthdev.goodreadsjapi.converter;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsResponse;
import org.jsoup.nodes.Document;

import java.util.List;

public abstract class DocumentToObjectConverter<T extends Entity> implements Converter<T> {

    // the last document converted from
    protected Document doc = null;

    // the data of the last document converted from
    protected String docData = null;

    // the instance of the last T object returned
    protected T prevT;

    @Override
    public T convertFrom(Document document) {
        if (doc != null && document.data().equals(docData)) {
            return prevT;
        }
        T t = parse(document);
        doc = document;
        docData = document.data();
        prevT = t;
        return t;
    }

    @Override
    public T convertFrom(GoodreadsResponse response) {
        return convertFrom(response.getDocument());
    }

    /**
     * Gets important information used to initialized a local {@link T}
     * variable.
     *
     * @param document The xml document
     * @return The elements to be used to initialize a variable
     */
    public abstract List<Entity> getElements(Document document);

    /**
     * Gets important information from the {@link GoodreadsResponse}. The important
     * information will be used to initialize a {@link T} variable.
     *
     * @param response The response to a request send to Goodreads
     * @return The elements that'll be used to initialize a {@link T} variable
     */
    public abstract List<Entity> getElements(GoodreadsResponse response);

    /**
     * Parses the {@code T} given a document
     *
     * @return The parsed {@link T} from a document
     */
    protected abstract T parse(Document document);

    /**
     * Parses the {@link T} given a {@link GoodreadsResponse}. Depending on the type
     * of {@link Entity} parsed, the method may invoke the other {@link #parse(Document)}
     * method with {@link GoodreadsResponse#getDocument()} as the argument.
     *
     * @param response The {@link GoodreadsResponse}
     * @return The parsed {@link T} from a {@link GoodreadsResponse}
     */
    protected abstract T parse(GoodreadsResponse response);

}
