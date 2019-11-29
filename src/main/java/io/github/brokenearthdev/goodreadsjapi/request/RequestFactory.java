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

import com.google.api.client.http.HttpContent;
import io.github.brokenearthdev.goodreadsjapi.authentication.GoodreadsOauth;

public class RequestFactory {

    private GoodreadsRequest.Verb verb = GoodreadsRequest.Verb.GET;
    private GoodreadsOauth oauth;
    private String url;
    private RequestParameters parameters;
    private HttpContent content;

    public RequestFactory setVerb(GoodreadsRequest.Verb verb) {
        this.verb = verb;
        return this;
    }

    public RequestFactory setOAuth(GoodreadsOauth oAuth) {
        this.oauth = oAuth;
        return this;
    }

    public RequestFactory setURL(String url) {
        this.url = url;
        return this;
    }

    public RequestFactory setRequestParameters(RequestParameters parameters) {
        this.parameters = parameters;
        return this;
    }

    public RequestFactory setHttpContent(HttpContent content) {
        this.content = content;
        return this;
    }

    public GoodreadsRequest build() {
        return new Request(url, parameters, oauth, verb, content);
    }

}
