package io.github.brokenearthdev.goodreadsjapi;

import com.google.api.client.http.HttpResponse;
import io.github.brokenearthdev.goodreadsjapi.entities.Author;
import io.github.brokenearthdev.goodreadsjapi.entities.Book;
import io.github.brokenearthdev.goodreadsjapi.request.*;
import org.apache.http.client.methods.RequestBuilder;
import org.jsoup.nodes.Document;

import java.util.List;

class AuthorImpl implements Author {

    private int id;

    AuthorImpl(int id, List<GoodreadsResponse> responses, List<Book> books) {
        this.id = id;
        this.responses= responses;
        this.books = books;
    }

    private List<GoodreadsResponse> responses;
    private List<Book> books;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public List<GoodreadsResponse> getResponses() {
        return responses;
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public boolean followAuthor() {
        GoodreadsRequest request = new RequestFactory()
                .setURL("https://www.goodreads.com/author_followings")
                .setVerb(GoodreadsRequest.Verb.POST)
                .setRequestParameters(new RequestParameters().addIfNotExists(new Parameter("id", id + ""))
                                        .addIfNotExists(new Parameter("format", "xml")))
                .build();
        try {
            request.sendRequest();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean unFollowAuthor() {
        GoodreadsRequest request = new RequestFactory()
                .setURL("https://www.goodreads.com/author_followings/AUTHOR_FOLLOWING_ID".
                        replace("AUTHOR_FOLLOWING_ID", id + ""))
                .setVerb(GoodreadsRequest.Verb.DELETE)
                .setRequestParameters(new RequestParameters().addIfNotExists(new Parameter("format", "xml")))
                .build();
        try {
            request.sendRequest();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
