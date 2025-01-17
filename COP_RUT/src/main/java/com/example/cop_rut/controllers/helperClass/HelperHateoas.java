package com.example.cop_rut.controllers.helperClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

public class HelperHateoas<T> extends RepresentationModel<HelperHateoas<T>> {
    private final T content;
    private final Map<String, Map<String, Object>> _actions = new HashMap<>();
    @JsonCreator
    public HelperHateoas(@JsonProperty("content") T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public Map<String, Map<String, Object>> getActions() {
        return _actions;
    }

    public void addAction(String name, String href, String method, String accept) {
        Map<String, Object> action = new HashMap<>();
        action.put("href", href);
        action.put("method", method);
        action.put("accept", accept);
        _actions.put(name, action);
    }

    public void addAction(String name, String href, String method) {
        Map<String, Object> action = new HashMap<>();
        action.put("href", href);
        action.put("method", method);
        _actions.put(name, action);
    }

}
