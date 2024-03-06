package org.example;

import org.example.dataModels.IVisitor;

import javax.ws.rs.core.CacheControl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resource {
    private String name;
    private boolean useEtags;
    private CacheControl cacheControl;
    private String pathElement;
    private final Map<String, String> attributes;
    private final Map<String, Link> links;
    private final List<Query> queries;
    private final List<Resource> subResources;
    private final List<State> states;

    public Resource() {
        attributes = new HashMap<>();
        links = new HashMap<>();
        queries = new ArrayList<>();
        subResources = new ArrayList<>();
        states = new ArrayList<>();
    }

    public void accept(IVisitor visitor) {
        visitor.enterResource(this);
        this.queries.forEach(q -> q.accept(visitor));
        this.subResources.forEach(s -> s.acceptSubResource(visitor));
        visitor.exitResource(this);
    }

    public void acceptSubResource(IVisitor visitor) {
        visitor.enterSubResource(this);
        this.queries.forEach(q -> q.acceptSubQuery(visitor));
        visitor.exitSubResource(this);
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isUseEtags() {
        return useEtags;
    }

    public void setUseEtags(boolean useEtags) {
        this.useEtags = useEtags;
    }

    public CacheControl getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(CacheControl cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getPathElement() {
        return pathElement;
    }

    public void setPathElement(String pathElement) {
        this.pathElement = pathElement;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Resource addAttribute(String type, String name) {
        attributes.put(name, type);
        return this;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public Resource addLink(String name, Link link) {
        links.put(name, link);
        return this;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public Resource addQuery(Query query) {
        queries.add(query);
        return this;
    }

    public List<Resource> getSubResources() {
        return subResources;
    }

    public Resource addSubResource(Resource subResource) {
        subResources.add(subResource);
        return this;
    }

    public List<State> getStates() {
        return states;
    }

    public Resource addState(State state) {
        states.add(state);
        return this;
    }
}
