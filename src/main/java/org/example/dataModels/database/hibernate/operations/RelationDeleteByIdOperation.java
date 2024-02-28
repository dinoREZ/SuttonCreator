package org.example.dataModels.database.hibernate.operations;

import org.example.dataModels.DataModel;

public class RelationDeleteByIdOperation implements DataModel {

    String primaryName;
    String secondaryName;
    String basePackage;

    public RelationDeleteByIdOperation() {
    }

    public RelationDeleteByIdOperation(String primaryName, String secondaryName, String basePackage) {
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
        return this.getPrimaryName() + this.getSecondaryName() + "DeleteByIdOperation.java";
    }
}
