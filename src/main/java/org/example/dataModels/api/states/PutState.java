package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import java.util.List;
import java.util.Objects;

public class PutState implements DataModel {

    private final String name;
    private final boolean useEtags;
    private final List<State> states;
    private final String basePackage;

    public PutState(String name, boolean useEtags, List<State> states, String basePackage) {
        this.name = name;
        this.useEtags = useEtags;
        this.states = states;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public boolean isUseEtags() {
        return useEtags;
    }

    public List<State> getStates() {
        return states;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Put" + name + "State.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PutState putState = (PutState) o;
        return useEtags == putState.useEtags && Objects.equals(name, putState.name) && Objects.equals(states, putState.states) && Objects.equals(basePackage, putState.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, useEtags, states, basePackage);
    }
}
