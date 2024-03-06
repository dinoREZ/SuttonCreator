package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

public class RelationUri implements DataModel {

    private final String primaryName;
    private final String secondaryName;
    private final String primaryPathElement;
    private final String secondaryPathElement;
    private final String basePackage;

    public RelationUri(String primaryName, String secondaryName, String primaryPathElement, String secondaryPathElement, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.primaryPathElement = primaryPathElement;
        this.secondaryPathElement = secondaryPathElement;
        this.basePackage = basePackage;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public String getPrimaryPathElement() {
        return primaryPathElement;
    }

    public String getSecondaryPathElement() {
        return secondaryPathElement;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return primaryName + secondaryName + "Uri.java";
    }
}
