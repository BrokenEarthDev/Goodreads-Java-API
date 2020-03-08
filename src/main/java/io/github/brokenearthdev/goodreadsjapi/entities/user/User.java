package io.github.brokenearthdev.goodreadsjapi.entities.user;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.entities.book.BookReview;

import java.net.URL;
import java.util.Date;
import java.util.List;

public interface User extends Entity {

    int getUserID();
    String getName();

    URL getURL();
    URL getImgURL();
    Date getLastActive();

    List<BookReview> getBookReviews();
    List<Friend> getFriends();

}

