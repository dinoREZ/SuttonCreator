package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class Uri implements DataModel {

    String name;
    String pathElement;
    String basePackage;

    public Uri(String name, String pathElement, String basePackage) {
        this.name = name;
        this.pathElement = pathElement;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public String getPathElement() {
        return pathElement;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return name + "Uri.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uri uri = (Uri) o;
        return Objects.equals(name, uri.name) && Objects.equals(pathElement, uri.pathElement) && Objects.equals(basePackage, uri.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pathElement, basePackage);
    }
}
