package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import java.util.List;
import java.util.Objects;

public class PutRelationState implements DataModel {

    String primaryName;
    String secondaryName;
    String basePackage;
    boolean useEtags;
    List<State> states;

    public PutRelationState(String primaryName, String secondaryName, boolean useEtags, List<State> states, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.basePackage = basePackage;
        this.useEtags = useEtags;
        this.states = states;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public boolean isUseEtags() {
        return useEtags;
    }

    public List<State> getStates() {
        return states;
    }

    @Override
    public String getOutputName() {
        return "Put" + primaryName + secondaryName + "State.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PutRelationState that = (PutRelationState) o;
        return useEtags == that.useEtags && Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(basePackage, that.basePackage) && Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, basePackage, useEtags, states);
    }
}
