package io.github.brokenearthdev.goodreadsjapi.entities;

import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsResponse;

import java.util.List;

public interface Entity {

    List<GoodreadsResponse> getResponses();

}
