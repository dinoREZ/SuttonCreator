package org.example.dataModels;

import java.util.Objects;

public class Start implements DataModel {

    private final String baseContextPath;
    private final String basePackage;

    public Start(String baseContextPath, String basePackage) {
        this.baseContextPath = baseContextPath;
        this.basePackage = basePackage;
    }

    public String getBaseContextPath() {
        return baseContextPath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Start.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Start start = (Start) o;
        return Objects.equals(baseContextPath, start.baseContextPath) && Objects.equals(basePackage, start.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseContextPath, basePackage);
    }
}
