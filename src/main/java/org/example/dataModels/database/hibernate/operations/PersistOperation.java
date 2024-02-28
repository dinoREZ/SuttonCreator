package org.example.dataModels.database.hibernate.operations;

import org.example.dataModels.DataModel;

public class PersistOperation implements DataModel {
    private String name;
    private String basePackage;

    public PersistOperation() {
    }

    public PersistOperation(String name, String basePackage) {
        this.name = name;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public String getOutputName() {
        return this.getName() + "PersistOperation.java";
    }
}
