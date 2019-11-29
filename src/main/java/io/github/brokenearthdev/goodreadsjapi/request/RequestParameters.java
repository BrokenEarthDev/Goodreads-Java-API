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

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * When sending a {@link GoodreadsRequest}, the {@link RequestParameters} will be embedded into the
 * request. A request parameter. Request parameters are specifications telling a website what to
 * look for. Request parameters often appear after the question mark in a {@link java.net.URL}
 */
public class RequestParameters implements Iterable<Parameter> {

    private List<Parameter> parameters = new LinkedList<>();

    /**
     * @return The request parameters
     */
    private List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Adds a request parameter. If a parameter with the same key exists, the parameter will be
     * replaced with the new one.
     *
     * @param parameter The parameter
     * @return {@code this} instance
     */
    public RequestParameters addRequestParameter(Parameter parameter) {
        parameters.add(parameter);
        return this;
    }

    /**
     * Adds a request parameter if no request parameters with the same key exists. If the same
     * parameter instance exists, the method will do nothing.
     *
     * @param parameter The request parameter
     * @return {@code this} instance
     */
    public RequestParameters addIfNotExists(Parameter parameter) {
        if (parameters.contains(parameter)) return this;
        if (!exists(parameter.getKey()))
        parameters.add(parameter);
        return this;
    }

    /**
     * Removes a request parameter if it exists. If otherwise, the method will do
     * nothing.
     *
     * @param parameter The request parameter
     * @return {@code this} instance
     */
    public RequestParameters removeRequestParameter(Parameter parameter) {
        parameters.remove(parameter);
        return this;
    }

    /**
     * Checks if a parameter with the same key exists. If it exists, {@code true} is returned.
     * Otherwise, the method will return {@code false}
     *
     * @param key The parameter key
     * @return Whether a parameter with the same key exists or not.
     */
    public boolean exists(String key) {
        for (Parameter p : parameters) {
            if (p.getKey().equals(key))
                return true;
        }
        return false;
    }

    /**
     * Gets a parameter with the same key as the {@link String} passed in. If none are found,
     * {@code null} is returned
     *
     * @param key The key
     * @return The {@link Parameter} found
     */
    public Parameter getParameter(String key) {
        for (Parameter parameter : parameters) {
            if (parameter.getKey().equals(key))
                return parameter;
        }
        return null;
    }

    /**
     * Gets a parameter in the index. If such an index doesn't exist, {@link IndexOutOfBoundsException}
     * will be thrown.
     *
     * @param index The index
     * @return The {@link Parameter}
     */
    public Parameter getParameter(int index) {
        return parameters.get(index);
    }

    /**
     * @return the number of {@link Parameter}s stored in this object.
     */
    public int size() {
        return parameters.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getParameters().size(); i++) {
            builder.append(getParameters().get(i));
            if (i + 1 != getParameters().size()) builder.append("&");
        }
        return builder.toString();
    }

    /**
     * Parses the request parameters and loads it into a {@link RequestParameters} object. If
     * no parameters are parsed either because no parameter exists or the parameters are written in
     * a wrong way, the method will return an empty {@link RequestParameters} object.
     * <p>
     * If a duplicate key is found while parsing, the first key parsed will be kept. The other
     * key-value pair won't be stored in the object.
     *
     * @param str The parameters
     * @return The parameters parsed into a {@link RequestParameters} object
     */
    public static RequestParameters parseParameters(String str) {
        String parameters = str;
        if (str.startsWith("?")) parameters = str.substring(str.indexOf("?"));
        RequestParameters reqParameters = new RequestParameters();
        StringBuilder parameterBuilder = new StringBuilder();
        char[] chars = parameters.toCharArray();
        for (char character : chars) {
            if (character == '&') {
                Parameter parsed = Parameter.parseParameter(parameterBuilder.toString());
                if (parsed != null) reqParameters.addIfNotExists(parsed);
                parameterBuilder = new StringBuilder();
            } else parameterBuilder.append(character);
        }
        return reqParameters;
    }

    /**
     * Adds the parameters from the object passed in. If there are duplicate parameters,
     * the parameter's value in the second object will replace the old one.
     *
     * @param requestParameters The {@link RequestParameters}
     */
    public void addParameters(RequestParameters requestParameters) {
        for (Parameter parameter : requestParameters) {
            addRequestParameter(parameter);
        }
    }

    /**
     * Combines the parameters in the url string passed in and the parameters present in
     * this class and returns a {@link String} with the url plus the combined parameters.
     * <p>
     * If there are duplicate keys, the key's value {@link String} will replace the value
     * in this object. This method will have no effect what so ever on the {@link Parameter}s
     * stored in this object. So if this method was called, the parameters in this object
     * will not change.
     * <p>
     * If the url passed in didn't have a parameter, the method will return this url with
     * this object's parameters
     *
     * @param url The url that will have its parameters combined with this object's parameters
     * @return The url with the combined parameters
     */
    public String combineParameters(String url) {
        RequestParameters parameters1 = parseParameters(url);
        if (parameters1.size() == 0)
            return url + "?" + toString();
        RequestParameters parameters2 = new RequestParameters();
        parameters2.addParameters(this);
        parameters2.addParameters(parameters1);
        int index = url.indexOf("?");
        String omitted = url.substring(0, index - 1);
        return omitted + "?" + parameters2;
    }

    /**
     * @inheritDocs
     */
    @NotNull
    @Override
    public Iterator<Parameter> iterator() {
        return parameters.iterator();
    }

}
