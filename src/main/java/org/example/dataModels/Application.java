package org.example.dataModels;

import org.example.Resource;

import java.util.List;
import java.util.Objects;

public class Application implements DataModel {

    private final List<Resource> resources;
    private final String basePackage;

    public Application(List<Resource> resources, String basePackage) {
        this.resources = resources;
        this.basePackage = basePackage;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Application.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(resources, that.resources) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resources, basePackage);
    }
}
