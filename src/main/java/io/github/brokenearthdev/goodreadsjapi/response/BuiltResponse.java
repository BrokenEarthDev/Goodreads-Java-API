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
package io.github.brokenearthdev.goodreadsjapi.response;

import com.google.api.client.http.HttpResponse;
import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsRequest;
import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;

/**
 * An implementation of the {@link GoodreadsResponse} interface.
 */
public class BuiltResponse implements GoodreadsResponse {

    /**
     * The request sent before this response
     */
    private GoodreadsRequest request;

    /**
     * The document parsed from a string
     */
    private Document document;

    /**
     * The {@link HttpResponse}
     */
    private HttpResponse response;

    /**
     * Initializes the object by passing in a {@link GoodreadsRequest}, a {@link Document} parsed from the
     * {@link HttpResponse}
     *
     *
     * @param request The {@link GoodreadsRequest} that was sent
     * @param document The {@link Document}
     * @param response The {@link HttpResponse}
     */
    public BuiltResponse(GoodreadsRequest request, Document document, HttpResponse response) {
        this.request = request;
        this.document = document;
        this.response = response;
    }

    /**
     * Initializes the object by passing in a {@link GoodreadsRequest} and a {@link HttpResponse}.
     * The constructor will then parse the {@link HttpResponse} into a Jsoup {@link Document} and
     * invoke {@link #BuiltResponse(GoodreadsRequest, Document, HttpResponse)}
     *
     * @param request The {@link GoodreadsRequest} that was sent
     * @param response The {@link HttpResponse}
     * @throws IOException If any parsing error occurred
     */
    public BuiltResponse(GoodreadsRequest request, HttpResponse response) throws IOException {
        this(request, Jsoup.parseBodyFragment(response.parseAsString()), response);
    }

    /**
     * @inheritDocs
     */
    @Override
    public Document getDocument() {
        return document;
    }

    /**
     * @inheritDocs
     */
    @Override
    public GoodreadsRequest getRequest() {
        return request;
    }

    /**
     * @inheritDocs
     */
    @Override
    public HttpResponse toHttpResponse() {
        return response;
    }
}
