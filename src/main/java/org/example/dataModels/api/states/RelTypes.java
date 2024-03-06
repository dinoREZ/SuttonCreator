package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class RelTypes implements DataModel {

    String name;
    String basePackage;

    public RelTypes(String name, String basePackage) {
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
        return name + "RelTypes.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelTypes relTypes = (RelTypes) o;
        return Objects.equals(name, relTypes.name) && Objects.equals(basePackage, relTypes.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePackage);
    }
}
