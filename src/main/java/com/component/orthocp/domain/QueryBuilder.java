package com.component.orthocp.domain;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Example;

public class QueryBuilder {
    public static Example<Component> makeQuery(Component component) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(component, exampleMatcher);
    }
    
}
