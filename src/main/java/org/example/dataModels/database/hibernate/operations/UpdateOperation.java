package org.example.dataModels.database.hibernate.operations;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class UpdateOperation implements DataModel {

    private String name;
    private String basePackage;

    public UpdateOperation() {
    }

    public UpdateOperation(String name, String basePackage) {
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
        return this.getName() + "UpdateOperation.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateOperation that = (UpdateOperation) o;
        return Objects.equals(name, that.name) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePackage);
    }
}
