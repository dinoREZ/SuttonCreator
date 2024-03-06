package org.example;

import org.apache.commons.lang3.tuple.Triple;
import org.example.dataModels.IVisitor;

import java.util.*;

public class Query {

    String subPathElement;
    List<Triple<String, String, String>> attributes; // type, name, defaultValue

    public Query() {
        attributes = new ArrayList<>();
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

    public List<Triple<String, String, String>> getAttributes() {
        return attributes;
    }

    public Query addAttribute(String type, String name, String defaultValue) {
        attributes.add(Triple.of(type, name, defaultValue));
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
