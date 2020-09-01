package io.github.brokenearthdev.goodreadsjapi.adapters;

import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;
import io.github.brokenearthdev.goodreadsjapi.internal.impl.BookImpl;
import io.github.brokenearthdev.goodreadsjapi.response.ResponsePath;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;
import io.github.brokenearthdev.goodreadsjapi.selector.NestedIndexSelector;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BookEntityAdapter extends EntityAdapter<Book> {

    private static final AuthorEntityAdapter ADAPTER = new AuthorEntityAdapter();

    @SuppressWarnings("MagicConstant")
    @Override
    public Book convert(ResponseSection section) throws Exception {

        // SECTIONS
        int id = Integer.parseInt(section.getElement("id", 0).text());
        int ratings_count = Integer.parseInt(section.getElement("ratings_count", 0).text());
        String name = section.getElement("title", 0).text();
        String format = section.getElement("format", 0).text();
        String publisher = section.getElement("publisher", 0).text();
        String description = section.getElement("description", 0).text();
        String isbn = section.getElement("isbn", 0).text();
        String isbn13 = section.getElement("isbn13", 0).text();
        URL link = new URL(section.getElement("link", 0).text());

        int publicationDay = Integer.parseInt(section.getElement("publication_day", 0).text());
        int publicationMonth = Integer.parseInt(section.getElement("publication_month", 0).text());
        int publicationYear = Integer.parseInt(section.getElement("publication_year", 0).text());
        Calendar calendar = Calendar.getInstance();
        calendar.set(publicationYear, publicationMonth - 1, publicationDay);
        Date date = calendar.getTime();

        float average_rating = Float.parseFloat(section.getElement("average_rating", 0).text());

        ResponseSection authors = section.subsection(new ResponsePath(section.getResponse()).append("authors"));
        List<ResponseSection> authorsList = new LinkedList<>();
        List<Author> list = new LinkedList<>();
        Elements es = authors.getContainedDocument().getElementsByTag("authors").get(0).getAllElements();
        for (int i = 0; i < es.size(); i++) {
            ResponsePath path = new ResponsePath(section.getResponse(), NestedIndexSelector.NESTED_INDEX_SELECTOR)
                                .append("author", i);
            authorsList.add(authors.subsection(path));
        }
        for (ResponseSection a : authorsList) {
            list.add(ADAPTER.convert(a));
        }
        // ----


        return new BookImpl(id, ratings_count, name, format, publisher, description, isbn, isbn13, link, date,
                average_rating, list, section.getResponse(), section);
    }
}
