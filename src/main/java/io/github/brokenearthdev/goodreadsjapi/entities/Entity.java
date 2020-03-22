package io.github.brokenearthdev.goodreadsjapi.entities;

import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;

import java.util.List;

public interface Entity {

    List<GoodreadsResponse> getResponses();

    enum Type {

        AUTHOR,
        USER,
        BOOK

    }

}
