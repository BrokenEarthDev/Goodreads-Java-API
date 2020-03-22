/*
Copyright 2019 BrokenEarthDev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.github.brokenearthdev.goodreadsjapi;

import io.github.brokenearthdev.goodreadsjapi.authentication.GoodreadsAuthentication;
import io.github.brokenearthdev.goodreadsjapi.authentication.GoodreadsOauth;
import io.github.brokenearthdev.goodreadsjapi.entities.book.Book;
import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;
import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;
import io.github.brokenearthdev.goodreadsjapi.request.Parameter;
import io.github.brokenearthdev.goodreadsjapi.request.RequestFactory;
import io.github.brokenearthdev.goodreadsjapi.request.RequestParameters;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class GoodreadsAPI {

    public static final String BASE_GOODREADS_URL = "https://www.goodreads.com";
    public static final String TOKEN_SERVER_URL = BASE_GOODREADS_URL + "/oauth/request_token";
    public static final String AUTHENTICATE_URL = BASE_GOODREADS_URL + "/oauth/authorize";
    public static final String ACCESS_TOKEN_URL = BASE_GOODREADS_URL + "/oauth/access_token";

    private String key;
    private String secret;
    private GoodreadsOauth oauth;

    public GoodreadsAPI(@NotNull String key, String secret) {
        Objects.requireNonNull(key, "Key can't be null");
        this.key = key;
        this.secret = secret;
        try {
            this.oauth = getOAuth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GoodreadsAPI(String key) {
        this(key, null);
    }

    public GoodreadsOauth getOAuth() throws IOException {
        if (oauth == null)
            oauth = new GoodreadsAuthentication(key, secret);
        return oauth;
    }

    public RequestFactory newRequestFactory() {
        return new RequestFactory();
    }

    public Author getAuthor(int id) {

        return null;
    }

    public Book getBook(long isbn) throws IOException {
        String link = "https://www.goodreads.com/book/isbn/" + isbn;
        GoodreadsResponse response = newRequestFactory()
                .setOAuth(oauth)
                .setURL(link)
                .setRequestParameters(new RequestParameters().addIfNotExists(new Parameter("key", key)))
                .build().sendRequest();
        System.out.println(link + "?" + response.getRequest().getParameters());
        return new GoodreadsBook(response.getDocument());
    }

}
