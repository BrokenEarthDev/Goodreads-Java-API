package io.github.brokenearthdev.goodreadsjapi.internal.impl;

import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;

import java.net.URL;

public class AuthorImpl implements Author {

    private int id, ratingsCount, textReviewsCount;
    private String name;
    private URL imgURL, link;
    private float avgRating;

    public AuthorImpl(int id, int ratingsCount, int textReviewsCount, String name, URL imgURL, URL link,
                      float avgRating) {
        this.id = id;
        this.ratingsCount = ratingsCount;
        this.textReviewsCount = textReviewsCount;
        this.name = name;
        this.imgURL = imgURL;
        this.link = link;
        this.avgRating = avgRating;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public URL getImageURL() {
        return imgURL;
    }

    @Override
    public URL getLink() {
        return link;
    }

    @Override
    public float getAverageRating() {
        return avgRating;
    }

    @Override
    public int getRatingsCount() {
        return ratingsCount;
    }

    @Override
    public int getTextReviewsCount() {
        return textReviewsCount;
    }
}
