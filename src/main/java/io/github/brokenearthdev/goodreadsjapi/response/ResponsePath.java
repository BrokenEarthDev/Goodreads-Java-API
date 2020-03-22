package io.github.brokenearthdev.goodreadsjapi.response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ResponsePath {

    ResponseSection getResponseSection();
    String getDirectory();
    LinkedHashMap<String, Integer> toMapPath();
    boolean exists();
    GoodreadsResponse getResponse();

}
