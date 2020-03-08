package io.github.brokenearthdev.goodreadsjapi.utils;

public enum RequestLinks {

    FOLLOW_AUTHOR("");

    private String link;

    RequestLinks(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return link;
    }

}