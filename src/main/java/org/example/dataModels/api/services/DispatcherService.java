package org.example.dataModels.api.services;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class DispatcherService implements DataModel {

    String basePackage;

    public DispatcherService(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "DispatcherService.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DispatcherService that = (DispatcherService) o;
        return Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePackage);
    }
}
