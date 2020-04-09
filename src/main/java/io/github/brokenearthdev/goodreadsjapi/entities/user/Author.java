package io.github.brokenearthdev.goodreadsjapi.entities.user;

import java.net.URL;

public interface Author extends User {

    int getID();
    String getName();

    URL getImageURL();
    URL getLink();

    float getAverageRating();
    int getRatingsCount();
    int getTextReviewsCount();
}
