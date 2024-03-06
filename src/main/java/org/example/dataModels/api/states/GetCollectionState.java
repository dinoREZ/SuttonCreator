package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import java.util.List;

public class GetCollectionState implements DataModel {

    private final String name;
    private final List<State> states;
    private final String basePackage;

    public GetCollectionState(String name, List<State> states, String basePackage) {
        this.name = name;
        this.states = states;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public List<State> getStates() {
        return states;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Get" + name + "CollectionState.java";
    }
}
