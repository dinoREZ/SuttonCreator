package org.example.dataModels.database;

import org.example.dataModels.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
