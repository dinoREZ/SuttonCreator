package org.example;

import java.util.Objects;

public class QueryParameter {

    private String type;
    private String name;
    private String defaultValue;
    private boolean isPathParameter;
    private ComparisonType comparisonType;

    public QueryParameter() {
    }

    public String getType() {
        return type;
    }

    public QueryParameter setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public QueryParameter setName(String name) {
        this.name = name;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public QueryParameter setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public boolean isPathParameter() {
        return isPathParameter;
    }

    public QueryParameter setPathParameter(boolean pathParameter) {
        isPathParameter = pathParameter;
        return this;
    }

    public ComparisonType getComparisonType() {
        return comparisonType;
    }

    public QueryParameter setComparisonType(ComparisonType comparisonType) {
        this.comparisonType = comparisonType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryParameter that = (QueryParameter) o;
        return isPathParameter == that.isPathParameter && Objects.equals(type, that.type) && Objects.equals(name, that.name) && Objects.equals(defaultValue, that.defaultValue) && comparisonType == that.comparisonType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, defaultValue, isPathParameter, comparisonType);
    }

    public enum ComparisonType {
        like, likeIgnoreCase, equal, greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo
    }
}
