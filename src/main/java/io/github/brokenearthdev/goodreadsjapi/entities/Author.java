package io.github.brokenearthdev.goodreadsjapi.entities;

import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsResponse;

import java.util.List;

public interface Author {

    /**
     * @return The author ID
     */
    int getID();

    /**
     * Gets the responses to the requests created by the API.
     *
     * @return The responses to the request for the information about the
     * author
     */
    List<GoodreadsResponse> getResponses();

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

}

