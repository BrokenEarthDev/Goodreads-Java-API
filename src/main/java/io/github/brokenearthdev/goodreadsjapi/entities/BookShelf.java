package io.github.brokenearthdev.goodreadsjapi.entities;

import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.entities.user.User;

import java.util.List;

public interface BookShelf extends Entity {

    List<Book> getBook();
    User getOwner();

}
