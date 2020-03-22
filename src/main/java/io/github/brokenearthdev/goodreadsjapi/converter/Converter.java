package io.github.brokenearthdev.goodreadsjapi.converter;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;
import org.jsoup.nodes.Document;

public abstract class Converter<T extends Entity> {

    private final GoodreadsResponse response;
    private final Document document;

    public Converter(GoodreadsResponse response, Document document) {
        this.response = response;
        this.document = document;
    }



}
