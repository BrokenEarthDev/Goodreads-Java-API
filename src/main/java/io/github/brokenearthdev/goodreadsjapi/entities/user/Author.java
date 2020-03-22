package io.github.brokenearthdev.goodreadsjapi.entities.user;

import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;

import java.util.List;

public interface Author extends User {

    /**
     * @return The author ID
     */
    int getID();

    /**
     * Gets the books the author created.
     *
     * @return The books created by the author
     */
    List<Book> getBooks();

    /**
     * Follows the author.
     *
     * @return Whether the operation is successful or not.
     */
    boolean followAuthor();

    /**
     * Unfollows the author
     *
     * @return Whether the operation is successful or not.
     */
    boolean unFollowAuthor();

    /**
     * Gets the responses to the requests created by the API.
     *
     * @return The responses to the request for the information about the
     * author
     */
    @Override
    List<GoodreadsResponse> getResponses();

}

