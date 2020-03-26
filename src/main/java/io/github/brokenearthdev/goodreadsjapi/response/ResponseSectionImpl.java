package io.github.brokenearthdev.goodreadsjapi.response;

import com.google.api.client.http.HttpResponse;
import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.request.GoodreadsRequest;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;

class ResponseSectionImpl implements ResponseSection {

    private ResponsePath path;
    private Document contained;

    ResponseSectionImpl(ResponsePath path, Document contained) {
        this.path = path;
        this.contained = contained;
    }


    @Override
    public Document getContainingDocument() {
        return contained;
    }

    @Override
    public Document getDocument() {
        return path.getResponse().getDocument();
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
    public @Nullable Entity convertToEntity() {
        return null;
    }
}
