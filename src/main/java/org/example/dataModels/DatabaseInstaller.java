package org.example.dataModels;

import java.util.Objects;

public class DatabaseInstaller implements DataModel {

    private final String basePackage;

    public DatabaseInstaller(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "DatabaseInstaller.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseInstaller that = (DatabaseInstaller) o;
        return Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePackage);
    }
}
