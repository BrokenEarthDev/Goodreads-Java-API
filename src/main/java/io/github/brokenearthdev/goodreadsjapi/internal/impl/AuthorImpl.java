package io.github.brokenearthdev.goodreadsjapi.internal.impl;

import io.github.brokenearthdev.goodreadsjapi.adapters.BookEntityAdapter;
import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;
import io.github.brokenearthdev.goodreadsjapi.response.ResponsePath;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;
import io.github.brokenearthdev.goodreadsjapi.selector.NestedIndexSelector;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class AuthorImpl extends ResponseSectionImpl implements Author {

    private final BookEntityAdapter ADAPTER = new BookEntityAdapter();

    private int id, followerCount, worksCount;
    private String name, hometown, bornAt, diedAt, gender;
    private URL imgURL, link;

    private List<Book> books = null;
    private ResponseSection section;

    public AuthorImpl(int id, int followerCount, int worksCount, String name, String hometown, String bornAt,
                      String diedAt, String gender, URL imgURL, URL link, ResponseSection section) {
        super(section.getPath(), section.getContainedDocument());
        this.id = id;
        this.followerCount = followerCount;
        this.worksCount = worksCount;
        this.name = name;
        this.imgURL = imgURL;
        this.link = link;
        this.hometown = hometown;
        this.bornAt = bornAt;
        this.diedAt = diedAt;
        this.gender = gender;
        this.section = section;
    }

    private void bookSearch() {
        System.out.println(section.getContainedDocument());
        ResponsePath path = new ResponsePath(section.getPath()).append("books");
        ResponseSection booksResponseSection = path.findSection();
        System.out.println(booksResponseSection);
        List<ResponseSection> responseList = new LinkedList<>();
        Elements elements = booksResponseSection.getContainedDocument().children().get(0).children();
        //System.out.println("XXX" + elements.size() + "V");
        for (int i = 0; i < elements.size(); i++) {
            responseList.add(booksResponseSection.subsection(new ResponsePath(booksResponseSection.getResponse(),
                    NestedIndexSelector.NESTED_INDEX_SELECTOR).append("book", i)));
        }

        List<Book> books = new LinkedList<>();
        try {
            for (ResponseSection responseSection : responseList) {
                books.add(ADAPTER.convert(responseSection));
            }
        } catch (Exception e) {
            this.books = new LinkedList<>();
        }
        this.books = books;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getWorksCount() {
        return worksCount;
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
    public int getFollowerCount() {
        return followerCount;
    }


    @Override
    public String getHomeTown() {
        return hometown;
    }

    @Override
    public String getBornAt() {
        return bornAt;
    }

    @Override
    public String getDiedAt() {
        return diedAt;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public List<Book> getBooks() {
        if (books == null)
            bookSearch();
        return books;
    }


}
