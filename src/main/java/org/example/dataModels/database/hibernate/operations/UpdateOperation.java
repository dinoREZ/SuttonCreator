package org.example.dataModels.database.hibernate.operations;

public class UpdateOperation {

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
}
