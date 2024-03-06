package org.example.dataModels.api.states;

import org.example.Resource;
import org.example.dataModels.DataModel;

import java.util.List;

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
}
