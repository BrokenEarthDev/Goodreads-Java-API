package io.github.brokenearthdev.goodreadsjapi.entities;

import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;

public interface Entity extends ResponseSection {

    GoodreadsResponse getResponse();

}
