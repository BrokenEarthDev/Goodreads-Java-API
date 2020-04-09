package io.github.brokenearthdev.goodreadsjapi.internal.impl;

import io.github.brokenearthdev.goodreadsjapi.GoodreadsAPI;
import io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse;
import io.github.brokenearthdev.goodreadsjapi.response.ResponsePath;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;
import io.github.brokenearthdev.goodreadsjapi.selector.NestedDefaultSelector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ResponseSectionImpl implements ResponseSection {

    private ResponsePath path;
    private Document contained;

    public ResponseSectionImpl(ResponsePath path, Document contained) {
        this.path = path;
        this.contained = contained;
    }


    @Override
    public Document getContainingDocument() {
        return path.getResponse().getDocument();
    }

    @Override
    public Document getContainedDocument() {
        return contained;
    }

    @Override
    public ResponsePath getPath() {
        return path;
    }

    @Override
    public GoodreadsResponse getResponse() {
        return path.getResponse();
    }

    @Override
    public ResponseSection subsection(ResponsePath path) {
        ResponsePath path1 = new ResponsePath(path);
        path1.toMultitagContainer().combine(path.toMultitagContainer());
        return path1.findSection();
    }

    @Override
    public Element getElement(String name, int index) {
       return NestedDefaultSelector.DEFAULT.getElementOfType(getContainedDocument().getAllElements(), name, index);
    }

    //todo fix (make more user-friendly)
    @Override
    public <T> T convertToEntity(Class<T> clazz) {
        try {
            return (T) GoodreadsAPI.CLASS_TYPE_ADAPTER_MAP.get(clazz).deserialize(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
