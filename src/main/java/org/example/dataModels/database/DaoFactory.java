package org.example.dataModels.database;

import org.example.Resource;
import org.example.dataModels.DataModel;

import java.util.*;

public class DaoFactory implements DataModel {

    String basePackage;
    boolean usesInMemory;
    List<Resource> resources;

    public DaoFactory() {
        resources = new ArrayList<>();
    }

    public DaoFactory(String basePackage, boolean usesInMemory, List<Resource> resources) {
        this.basePackage = basePackage;
        this.usesInMemory = usesInMemory;
        this.resources = resources;
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

    public List<Resource> getResources() {
        return resources;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
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
        return usesInMemory == that.usesInMemory && Objects.equals(basePackage, that.basePackage) && Objects.equals(resources, that.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePackage, usesInMemory, resources);
    }
}
