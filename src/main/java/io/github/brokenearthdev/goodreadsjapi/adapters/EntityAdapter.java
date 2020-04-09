package io.github.brokenearthdev.goodreadsjapi.adapters;

import io.github.brokenearthdev.goodreadsjapi.entities.Entity;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;

public abstract class EntityAdapter<T extends Entity> {

    public abstract ResponseSection serialize(T entity);
    public abstract T deserialize(ResponseSection section) throws Exception;

}
