package io.github.brokenearthdev.goodreadsjapi.internal.impl;

import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;
import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;

import java.net.URL;
import java.util.Date;
import java.util.List;

public class BookImpl extends ResponseSectionImpl implements Book {

    private int id, ratingsCount;
    private String name, format, publisher, desc, isbn, isbn13;
    private URL link;
    private Date publicationDate;
    private float avgRating;
    private List<Author> authors;
    private GoodreadsResponse response;

    public BookImpl(int id, int ratingsCount, String name, String format, String publisher, String desc, String isbn,
                    String isbn13, URL link, Date publicationDate, float avgRating, List<Author> authors,
                    GoodreadsResponse response, ResponseSection section) {
        super(section.getPath(), section.getContainedDocument());
        this.id = id;
        this. ratingsCount = ratingsCount;
        this.name = name;
        this.format = format;
        this.publisher = publisher;
        this.desc = desc;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.link = link;
        this.publicationDate = publicationDate;
        this.avgRating = avgRating;
        this.authors = authors;
        this.response = response;
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
    public URL getLink() {
        return link;
    }

    @Override
    public String getISBN() {
        return isbn;
    }

    @Override
    public String getISBN13() {
        return isbn13;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    @Override
    public Date getPublicationDate() {
        return publicationDate;
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
    public String getDescription() {
        return desc;
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public GoodreadsResponse getResponse() {
        return response;
    }


}
