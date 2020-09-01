package io.github.brokenearthdev.goodreadsjapi.adapters;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;

public abstract class EntityAdapter<T extends Entity> {

    public ResponseSection toResponseSection(T t) {
        return (ResponseSection) t;
    }

    public abstract T convert(ResponseSection section) throws Exception;

}
