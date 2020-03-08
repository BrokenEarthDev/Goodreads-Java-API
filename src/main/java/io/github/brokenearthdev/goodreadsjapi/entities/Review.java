package io.github.brokenearthdev.goodreadsjapi.entities;

import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;
import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsResponse;

public interface Review extends Entity {

    String getReviewerName();
    Author getTargetAuthor();

}
