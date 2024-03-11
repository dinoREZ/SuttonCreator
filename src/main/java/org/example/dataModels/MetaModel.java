package org.example.dataModels;

import org.example.Resource;

import javax.ws.rs.core.CacheControl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MetaModel {
    private final List<Resource> primaryResources;
    private String basePackage;
    private String baseContextPath;
    private boolean usesInMemory;

    public MetaModel() {
        primaryResources = new ArrayList<>();
    }

    public void accept(IVisitor visitor) {
        visitor.enterMetaModel(this);
        this.getResources().forEach(r -> r.accept(visitor));
        visitor.exitMetaModel(this);
    }

    public List<Resource> getResources() {
        return primaryResources;
    }

    public MetaModel addResource(Resource resource) {
        primaryResources.add(resource);
        return this;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public MetaModel setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    public String getBaseContextPath() {
        return baseContextPath;
    }

    public MetaModel setBaseContextPath(String baseContextPath) {
        this.baseContextPath = baseContextPath;
        return this;
    }

    public boolean usesInMemory() {
        return usesInMemory;
    }

    public MetaModel setUsesInMemory(boolean usesInMemory) {
        this.usesInMemory = usesInMemory;
        return this;
    }

    public MetaModel setUseEtagsForAllResources(boolean useEtags) {
        getResources().forEach(resource -> resource.setUseEtags(useEtags));
        return this;
    }

    public MetaModel setCacheControlForAllResources(CacheControl cacheControl) {
        getResources().forEach(resource -> resource.setCacheControl(cacheControl));
        return this;
    }
}
