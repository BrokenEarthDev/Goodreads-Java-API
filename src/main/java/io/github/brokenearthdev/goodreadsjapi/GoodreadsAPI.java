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


import io.github.brokenearthdev.goodreadsjapi.adapters.EntityAdapter;
import io.github.brokenearthdev.goodreadsjapi.authentication.GoodreadsAuthentication;
import io.github.brokenearthdev.goodreadsjapi.authentication.GoodreadsOauth;
import io.github.brokenearthdev.goodreadsjapi.entities.Entity;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GoodreadsAPI {

    public static final String BASE_GOODREADS_URL = "https://www.goodreads.com";
    public static final String TOKEN_SERVER_URL = BASE_GOODREADS_URL + "/oauth/request_token";
    public static final String AUTHENTICATE_URL = BASE_GOODREADS_URL + "/oauth/authorize";
    public static final String ACCESS_TOKEN_URL = BASE_GOODREADS_URL + "/oauth/access_token";

    private final String key;
    private String secret;
    private GoodreadsOauth oauth;

    public GoodreadsAPI(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public boolean authenticate() throws IOException {
        oauth = new GoodreadsAuthentication(key, secret);
        return true;
    }


    public GoodreadsOauth getOauth() {
        return oauth;
    }

}
