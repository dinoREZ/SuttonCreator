package org.example.dataModels.api.states;

import org.example.Resource;
import org.example.dataModels.DataModel;

import java.util.List;
import java.util.Objects;

public class GetDispatcherState implements DataModel {

    List<Resource> resources;
    String basePackage;

    public GetDispatcherState(List<Resource> resources, String basePackage) {
        this.resources = resources;
        this.basePackage = basePackage;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "GetDispatcherState.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDispatcherState that = (GetDispatcherState) o;
        return Objects.equals(resources, that.resources) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resources, basePackage);
    }
}
