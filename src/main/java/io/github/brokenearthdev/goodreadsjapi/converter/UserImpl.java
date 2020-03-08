package io.github.brokenearthdev.goodreadsjapi.converter;

import io.github.brokenearthdev.goodreadsjapi.entities.book.BookReview;
import io.github.brokenearthdev.goodreadsjapi.entities.user.Friend;
import io.github.brokenearthdev.goodreadsjapi.entities.user.User;
import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsResponse;

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class UserImpl implements User {

    private int userID;
    private String name;
    private URL url;
    private URL imgURL;
    private Data lastActive;
    private List<BookReview> bookReviews;
    private List<Friend> friends;
    private List<GoodreadsResponse> responses;

    public UserImpl(int userID) {
        this.userID = userID;
    }


    @Override
    public int getUserID() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public URL getImgURL() {
        return null;
    }

    @Override
    public Date getLastActive() {
        return null;
    }

    @Override
    public List<BookReview> getBookReviews() {
        return null;
    }

    @Override
    public List<Friend> getFriends() {
        return null;
    }

    @Override
    public List<GoodreadsResponse> getResponses() {
        return null;
    }
}
