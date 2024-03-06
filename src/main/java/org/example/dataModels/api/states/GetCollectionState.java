package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCollectionState that = (GetCollectionState) o;
        return Objects.equals(name, that.name) && Objects.equals(states, that.states) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, states, basePackage);
    }
}
