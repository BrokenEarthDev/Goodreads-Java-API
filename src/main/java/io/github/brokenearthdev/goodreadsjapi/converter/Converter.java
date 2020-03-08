package io.github.brokenearthdev.goodreadsjapi.converter;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsResponse;
import org.jsoup.nodes.Document;

/**
 * Any subclass of this type contains methods that allows you to convert data present in
 * xml format (usually returned from Goodreads) to java objects. For example, a subclass
 * could convert a response (to a command that tells it to get information about a book) to
 * a {@link io.github.brokenearthdev.goodreadsjapi.entities.book.Book} object.
 * <p>
 * Please note that this class isn't intended for public usage, so certain issues may arise
 * if not used properly
 *
 * @param <T> A subclass of Entity. A subclass of this class of type {@code T} will be able to
 *           handle conversions with this type.
 */
public interface Converter<T extends Entity> {

    T convertFrom(Document document);
    T convertFrom(GoodreadsResponse response);

}
