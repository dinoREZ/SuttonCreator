package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

public class RelTypes implements DataModel {

    String name;
    String basePackage;

    public RelTypes(String name, String basePackage) {
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
        return name + "RelTypes.java";
    }
}
