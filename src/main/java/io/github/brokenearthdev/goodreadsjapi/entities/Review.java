package io.github.brokenearthdev.goodreadsjapi.entities;

import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;

public interface Review extends Entity {

    String getReviewerName();
    Author getTargetAuthor();

}
