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

/**
 * A {@link Parameter} is a key-value set that is a part of a request parameters (often appears after a
 * question mark sign). The key and value is separated by an equal sign.
 */
public class Parameter {
    private String key, value;

    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Parses a parameter in a given {@link String}. If a parameter can't be parsed, {@code null}
     * is returned.
     *
     * @param str The {@link String} to parse
     * @return A {@link Parameter} object containing the parameter data, or null if can't be parsed.
     */
    public static Parameter parseParameter(String str) {
        String parameter = str;
        if (str.startsWith("?"))
            parameter = str.substring(1);
        int sep = str.indexOf('=');
        if (sep <= 0) return null;
        parameter = parameter.replace("?", "%3F");
        String key = parameter.substring(0, sep - 1);
        String value = parameter.substring(sep - 1).replace("=", "");
        if (value.contains("=")) return null;
        return new Parameter(key, value);
    }

    /**
     * @return The key of the parameter
     */
    public String getKey() {
        return key;
    }

    /**
     * @return The value of the parameter
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
