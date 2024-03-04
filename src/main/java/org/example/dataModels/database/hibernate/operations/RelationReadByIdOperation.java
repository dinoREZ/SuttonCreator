package org.example.dataModels.database.hibernate.operations;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class RelationReadByIdOperation implements DataModel {
    String primaryName;
    String secondaryName;
    String basePackage;

    public RelationReadByIdOperation() {
    }

    public RelationReadByIdOperation(String primaryName, String secondaryName, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.basePackage = basePackage;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public String getOutputName() {
        return this.getPrimaryName() + this.getSecondaryName() + "ReadByIdOperation.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationReadByIdOperation that = (RelationReadByIdOperation) o;
        return Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, basePackage);
    }
}
