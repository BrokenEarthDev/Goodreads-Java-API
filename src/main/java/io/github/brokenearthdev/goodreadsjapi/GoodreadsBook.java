package io.github.brokenearthdev.goodreadsjapi;

import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.response.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.LinkedList;
import java.util.List;

public class GoodreadsBook implements Book {

    private String author;
    private int id;
    private String isbn;
    private List<GoodreadsResponse> responses;
    private GoodreadsResponse response;

    private final Document document;

    public GoodreadsBook(GoodreadsResponse response) {
        this.response = response;
        this.document = response.getDocument();
    }

    @Override
    public String getAuthor() {
        if (author == null) {
            ResponsePath path = new ResponsePathBuilder(response)
                    .appendSubdirectory("book", 0)
                    .appendSubdirectory("authors", 0)
                    .appendSubdirectory("author", 0)
                    .build();
            ResponseSection section = path.getResponseSection();
            this.author = section.getDocument().body().child(1).text();
        }
        return author;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getISBN() {
        return null;
    }

    @Override
    public List<GoodreadsResponse> getResponses() {
        return null;
    }
}

/*
if (author == null) {
            Element main = document.getElementsByTag("GoodreadsResponse").get(0);
            if (main == null) return "{NULL}";
            Element bookElement = null;
            Element authorElement = null;
            for (Element element : main.children()) {
                if (element.tagName().equals("book")) {
                    bookElement = element;
                    break;
                }
            }
            if (bookElement == null) return "{NULL}";
            for (Element element : bookElement.children()) {
                if (element.tagName().equals("authors")) {
                    authorElement = element;
                    break;
                }
            }
            if (authorElement == null) return "{NULL}";
            List<String> authors = new LinkedList<>();
            for (Element child : authorElement.children()) {
                if (child.tagName().equals("author")) {
                    Element author_ = child.children().get(1);
                    String name = author_.toString().replace("<" + author_.tagName() + ">",
                            "").replace("</" + author_.tagName() + ">", "");
                    authors.add(name);
                }
            }
            StringBuilder authorBuilder = new StringBuilder();
            authors.forEach(e -> {
                if (authors.size() == 1) {
                    this.author = e;
                } else {
                    if (author == null) authorBuilder.append("{");
                    authorBuilder.append(e).append(", ");
                }
            });
            // todo improve the readability and shorten code
            //if (authors.size() != 1)
            //author = authorBuilder.substring(0, authorBuilder.length()).append("}").toString(); //todo fix
        }
 */
