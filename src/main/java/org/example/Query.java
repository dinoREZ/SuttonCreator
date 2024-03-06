package org.example;

import org.example.dataModels.IVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Query {

    String subPathElement;
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

    public String getSubPathElement() {
        return subPathElement;
    }

    public Query setSubPathElement(String subPathElement) {
        this.subPathElement = subPathElement;
        return this;
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
