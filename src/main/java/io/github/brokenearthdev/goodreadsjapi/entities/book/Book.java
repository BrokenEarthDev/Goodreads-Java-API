package io.github.brokenearthdev.goodreadsjapi.entities.book;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;

import java.net.URL;
import java.util.Date;
import java.util.List;

public interface Book extends Entity {

    int getID();
    String getName();
    URL getLink();

    String getISBN();
    String getISBN13();

    String getFormat();
    String getPublisher();
    Date getPublicationDate();

    float getAverageRating();
    int getRatingsCount();
    String getDescription();

    List<Author> getAuthors();

}
