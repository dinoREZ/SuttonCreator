package org.example.dataModels.api.queries;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class ReadAllQuery implements DataModel {

    private final String name;
    private final String basePackage;

    public ReadAllQuery(String name, String basePackage) {
        this.name = name;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return name + "ReadAllQuery.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadAllQuery that = (ReadAllQuery) o;
        return Objects.equals(name, that.name) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePackage);
    }
}
