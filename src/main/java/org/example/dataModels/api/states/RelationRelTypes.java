package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationRelTypes that = (RelationRelTypes) o;
        return Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, basePackage);
    }
}
