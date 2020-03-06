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
package io.github.brokenearthdev.goodreadsjapi.request;

import com.google.api.client.auth.oauth.OAuthGetAccessToken;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.*;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import io.github.brokenearthdev.goodreadsjapi.GoodreadsAPI;
import io.github.brokenearthdev.goodreadsjapi.authentication.GoodreadsOauth;

import java.io.IOException;
import java.net.URL;

/**
 * A class responsible for sending requests to goodreads.com
 */
class Request implements GoodreadsRequest {

    private String baseURL;
    private RequestParameters parameters;
    private GoodreadsOauth oauth;
    private Verb verb;
    private HttpContent content;

    public Request(String baseURL, RequestParameters parameters, GoodreadsOauth oauth, Verb verb, HttpContent content) {
        this.baseURL = baseURL;
        this.parameters = parameters;
        this.oauth = oauth;
        this.content = content;
        this.verb = verb;
    }

    @Override
    public String getURL() {
        return baseURL;
    }

    @Override
    public RequestParameters getParameters() {
        return parameters;
    }

    @Override
    public GoodreadsResponse sendRequest() throws IOException {
        OAuthParameters parameters = new OAuthParameters();
        parameters.signer = oauth.getSigner();
        parameters.consumerKey = oauth.getKey();
        parameters.token = oauth.getAccessTokenResponse().token;
        String combined = this.parameters.combineParameters(baseURL);
        HttpRequestFactory factory = new ApacheHttpTransport().createRequestFactory(parameters);
        HttpRequest request;
        if (verb.getId() == 0)
            request = factory.buildGetRequest(new GenericUrl(combined));
        else if (verb.getId() == 1)
            request = factory.buildPostRequest(new GenericUrl(combined), content);
        else if (verb.getId() == 2)
            request = factory.buildPutRequest(new GenericUrl(combined), content);
        else if (verb.getId() == 3)
            request = factory.buildDeleteRequest(new GenericUrl(combined));
        else if (verb.getId() == 4) request = factory.buildPatchRequest(new GenericUrl(combined), content);
        else request = factory.buildHeadRequest(new GenericUrl(combined));
        return new BuiltResponse(this, request.execute());
    }



}
