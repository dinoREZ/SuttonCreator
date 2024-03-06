package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

public class RelationRelTypes implements DataModel {

    private final String primaryName;
    private final String secondaryName;
    private final String basePackage;

    public RelationRelTypes(String primaryName, String secondaryName, String basePackage) {
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
        return primaryName + secondaryName + "RelTypes.java";
    }
}
