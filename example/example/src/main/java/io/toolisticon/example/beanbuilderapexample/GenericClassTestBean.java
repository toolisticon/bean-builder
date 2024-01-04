package io.toolisticon.example.beanbuilderapexample;

import io.toolisticon.beanbuilder.api.BeanBuilder;

import java.util.Collection;

@BeanBuilder(className = "GenericsBuilder")
public class GenericClassTestBean <E, T extends Collection<E>> {

    private T collection;


    public T getCollection() {
        return collection;
    }

    public void setCollection(T collection) {
        this.collection = collection;
    }
}
