package org.example;

import org.example.dataModels.IVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Query {
    Map<String, String> attributes;

    public Query() {
        attributes = new HashMap<>();
    }

    public void accept(IVisitor visitor) {
        visitor.enterQuery(this);
        visitor.exitQuery(this);
    }

    public void acceptSubQuery(IVisitor visitor) {
        visitor.enterSubQuery(this);
        visitor.exitSubQuery(this);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Query addAttribute(String type, String name) {
        attributes.put(name, type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(attributes, query.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes);
    }
}
