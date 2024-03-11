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
    private String defaultSorting;
    private boolean usePaging;
    private int defaultPagingOffset;
    private int defaultPagingSize;
    private final Map<String, String> attributes;
    private final Map<String, Link> links;
    private final List<Query> queries;
    private final List<Resource> subResources;

    public Resource() {
        attributes = new HashMap<>();
        links = new HashMap<>();
        queries = new ArrayList<>();
        subResources = new ArrayList<>();
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

    public boolean isDefaultGetNeeded() {
        // If there is a query with an empty SubPathElement, it replaces the default get
        return this.queries.stream().noneMatch(query -> "".equals(query.getSubPathElement()));
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

    public Resource setUseEtags(boolean useEtags) {
        this.useEtags = useEtags;
        return this;
    }

    public CacheControl getCacheControl() {
        return cacheControl;
    }

    public Resource setCacheControl(CacheControl cacheControl) {
        this.cacheControl = cacheControl;
        return this;
    }

    public String getPathElement() {
        return pathElement;
    }

    public Resource setPathElement(String pathElement) {
        this.pathElement = pathElement;
        return this;
    }

    public String getDefaultSorting() {
        return defaultSorting;
    }

    public Resource setDefaultSorting(String defaultSorting) {
        this.defaultSorting = defaultSorting;
        return this;
    }

    public boolean isUsePaging() {
        return usePaging;
    }

    public Resource setUsePaging(boolean usePaging) {
        this.usePaging = usePaging;
        return this;
    }

    public int getDefaultPagingOffset() {
        return defaultPagingOffset;
    }

    public Resource setDefaultPagingOffset(int defaultPagingOffset) {
        this.defaultPagingOffset = defaultPagingOffset;
        return this;
    }

    public int getDefaultPagingSize() {
        return defaultPagingSize;
    }

    public Resource setDefaultPagingSize(int defaultPagingSize) {
        this.defaultPagingSize = defaultPagingSize;
        return this;
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
}
