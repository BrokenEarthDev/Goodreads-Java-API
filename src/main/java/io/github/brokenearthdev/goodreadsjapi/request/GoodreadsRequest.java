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

import java.io.IOException;

/**
 * A request that was sent to Goodreads. To sent the request, call {@link #sendRequest()}. The request
 * that will be sent will be an HTML request, meaning that a {@link Verb} is required. To create a
 * {@link GoodreadsRequest}, use a {@link RequestFactory} or call it using the API.
 * <p>
 * There are 6 types of {@link Verb}s
 * <ul>
 *     <li>GET</li>
 *     <li>POST</li>
 *     <li>PUT</li>
 *     <li>DELETE</li>
 *     <li>PATCH</li>
 *     <li>HEAD</li>
 * </ul>
 * <p>
 * A {@link GoodreadsRequest} may require {@link RequestParameters}, which are specifications telling
 * Goodreads what to look for.
 */
public interface GoodreadsRequest {

    /**
     * The URL to the webpage. The {@link RequestParameters} are omitted from the URL. When
     * a request is to be sent, the {@link RequestParameters} will be combined with this URL.
     *
     * @return The URL
     */
    String getURL();

    /**
     * The {@link RequestParameters} that will be incorporated into the URL and then sent
     * to Goodreads to retrieve, create, or manage. A {@link RequestParameters} are
     * specifications telling what Goodreads to look for.
     * <p>
     * Please note that including parameters that does not exist (ie. parameters that
     * Goodreads won't understand) may not yield the correct result.
     *
     * @return The {@link RequestParameters}
     */
    RequestParameters getParameters();

    /**
     * Sends a request to Goodreads. The URL and {@link RequestParameters} are combined
     * and sent. The method will return a {@link GoodreadsResponse} object.
     *
     * @return A {@link GoodreadsResponse} object
     * @throws IOException If any IO error occurred
     */
    GoodreadsResponse sendRequest() throws IOException;

    /**
     * A {@link Verb} are instructions that tells the server what to do. This enum represents
     * HTTP requests, each constant is a type of HTTP request. There are 6 types of verbs
     * <p>
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     *     <li>PUT</li>
     *     <li>DELETE</li>
     *     <li>PATCH</li>
     *     <li>HEAD</li>
     * </ul>
     */
    enum Verb {
        GET(0), POST(1), PUT(2),
        DELETE(3), PATCH(4), HEAD(5);

        private int id;

        Verb (int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
    }

}
