package org.example.dataModels.database;

import org.example.dataModels.DataModel;

import java.util.*;

public class DaoFactory implements DataModel {

    String basePackage;
    boolean usesInMemory;
    List<String> resources;
    Map<String, List<String>> subResources;

    public DaoFactory() {
        resources = new ArrayList<>();
        subResources = new HashMap<>();
    }

    public DaoFactory(String basePackage, boolean usesInMemory, List<String> resources, Map<String, List<String>> subResources) {
        this.basePackage = basePackage;
        this.usesInMemory = usesInMemory;
        this.resources = resources;
        this.subResources = subResources;
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

    public Map<String, List<String>> getSubResources() {
        return subResources;
    }

    public void addSubResource(String primaryResource, List<String> subResources) {
        this.subResources.put(primaryResource, subResources);
    }

    @Override
    public String getOutputName() {
        return "DaoFactory.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaoFactory that = (DaoFactory) o;
        return usesInMemory == that.usesInMemory && Objects.equals(basePackage, that.basePackage) && Objects.equals(resources, that.resources) && Objects.equals(subResources, that.subResources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePackage, usesInMemory, resources, subResources);
    }
}
