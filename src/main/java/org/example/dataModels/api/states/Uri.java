package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

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
}
