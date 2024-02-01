package org.example.dataModels;

import org.example.Resource;

import java.util.ArrayList;
import java.util.List;

public class MetaModel {
    private final List<Resource> resources;
    private String basePackage;
    private boolean usesInMemory;

    public MetaModel() {
        resources = new ArrayList<>();
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

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public boolean usesInMemory() {
        return usesInMemory;
    }

    public void setUsesInMemory(boolean usesInMemory) {
        this.usesInMemory = usesInMemory;
    }
}
