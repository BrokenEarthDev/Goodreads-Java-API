package io.github.brokenearthdev.goodreadsjapi.response;

import io.github.brokenearthdev.goodreadsjapi.converter.Converter;
import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;

public interface ResponseSection {

    Document getContainingDocument();
    Document getDocument();
    ResponsePath getPath();
    GoodreadsResponse getResponse();
    @Nullable Entity convertToEntity();


}
