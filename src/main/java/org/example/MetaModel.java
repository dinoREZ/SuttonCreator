package org.example;

import java.util.ArrayList;
import java.util.List;

public class MetaModel {
    private final List<Resource> resources;
    private String basePackage;

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
}
