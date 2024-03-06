package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

public class PostRelationState implements DataModel {

    String primaryName;
    String secondaryName;
    String basePackage;

    public PostRelationState(String primaryName, String secondaryName, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.basePackage = basePackage;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Post" + primaryName + secondaryName + "State.java";
    }
}
