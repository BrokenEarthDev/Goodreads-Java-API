package io.github.brokenearthdev.goodreadsjapi.entities.user;

import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;

import java.net.URL;
import java.util.List;

public interface Author extends User {

    int getID();
    int getWorksCount();
    String getName();

    URL getImageURL();
    URL getLink();

    int getFollowerCount();

    String getHomeTown();
    String getBornAt();
    String getDiedAt();
    String getGender();

    List<Book> getBooks();
}
