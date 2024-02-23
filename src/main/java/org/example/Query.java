package org.example;

import org.example.dataModels.IVisitor;

import java.util.HashMap;
import java.util.Map;

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
}
