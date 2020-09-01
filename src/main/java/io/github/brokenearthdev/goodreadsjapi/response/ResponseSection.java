package io.github.brokenearthdev.goodreadsjapi.response;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface ResponseSection {

    Document getContainingDocument();
    Document getContainedDocument();
    ResponsePath getPath();
    GoodreadsResponse getResponse();

    ResponseSection subsection(ResponsePath path);
    Element getElement(String name, int index);

    <T> T convertToEntity(Class<T> clazz);

}
