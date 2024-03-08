package org.example.dataModels.api.rateLimiting;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class AnyApiKeyRateLimiter implements DataModel {

    String basePackage;

    public AnyApiKeyRateLimiter(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "AnyApiKeyRateLimiter.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyApiKeyRateLimiter that = (AnyApiKeyRateLimiter) o;
        return Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePackage);
    }
}
