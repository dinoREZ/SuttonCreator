package org.example;

import org.example.dataModels.IVisitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Query {

    String subPathElement;
    List<QueryParameter> queryParameters;

    public Query() {
        queryParameters = new ArrayList<>();
    }

    public void accept(IVisitor visitor) {
        visitor.enterQuery(this);
        visitor.exitQuery(this);
    }

    public void acceptSubQuery(IVisitor visitor) {
        visitor.enterSubQuery(this);
        visitor.exitSubQuery(this);
    }

    public String getSubPathElement() {
        return subPathElement;
    }

    public Query setSubPathElement(String subPathElement) {
        this.subPathElement = subPathElement;
        return this;
    }

    public List<QueryParameter> getPathQueryParameters() {
        return queryParameters.stream()
                .filter(QueryParameter::isPathParameter)
                .collect(Collectors.toList());
    }

    public List<QueryParameter> getNotPathQueryParameters() {
        return queryParameters.stream()
                .filter(Predicate.not(QueryParameter::isPathParameter))
                .collect(Collectors.toList());
    }

    public List<QueryParameter> getQueryParameters() {
        return queryParameters;
    }

    public Query addQueryParameter(QueryParameter queryParameter) {
        queryParameters.add(queryParameter);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(subPathElement, query.subPathElement) && Objects.equals(queryParameters, query.queryParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subPathElement, queryParameters);
    }
}
