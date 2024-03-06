package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

public class PostState implements DataModel {

    String name;
    String basePackage;

    public PostState(String name, String basePackage) {
        this.name = name;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Post" + name + "State.java";
    }
}
