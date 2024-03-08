package org.example.dataModels.api.security;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class NoAuthNeededAuthenticationProvider implements DataModel {

    String basePackage;

    public NoAuthNeededAuthenticationProvider(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "NoAuthNeededAuthenticationProvider.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoAuthNeededAuthenticationProvider that = (NoAuthNeededAuthenticationProvider) o;
        return Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePackage);
    }
}
