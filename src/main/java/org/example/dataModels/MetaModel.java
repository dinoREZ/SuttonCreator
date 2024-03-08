package org.example.dataModels;

import org.example.Resource;

import java.util.ArrayList;
import java.util.List;

public class MetaModel {
    private final List<Resource> resources;
    private String basePackage;
    private String baseContextPath;
    private boolean usesInMemory;

    public MetaModel() {
        resources = new ArrayList<>();
    }

    public void accept(IVisitor visitor) {
        visitor.enterMetaModel(this);
        this.resources.forEach(r -> r.accept(visitor));
        visitor.exitMetaModel(this);
    }

    public List<Resource> getResources() {
        return resources;
    }

    public MetaModel addResource(Resource resource) {
        resources.add(resource);
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

    public void setUsesInMemory(boolean usesInMemory) {
        this.usesInMemory = usesInMemory;
    }
}
