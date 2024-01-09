package org.example.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoFactory {

    String basePackage;
    boolean usesInMemory;
    List<String> resources;
    Map<String, String> subResources;

    public DaoFactory() {
        resources = new ArrayList<>();
        subResources = new HashMap<>();
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public boolean isUsesInMemory() {
        return usesInMemory;
    }

    public void setUsesInMemory(boolean usesInMemory) {
        this.usesInMemory = usesInMemory;
    }

    public List<String> getResources() {
        return resources;
    }

    public void addResource(String name) {
        resources.add(name);
    }

    public Map<String, String> getSubResources() {
        return subResources;
    }

    public void addSubResource(String primaryResource, String subResource) {
        subResources.put(primaryResource, subResource);
    }
}
