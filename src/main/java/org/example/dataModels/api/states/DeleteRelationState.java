package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import java.util.List;

public class DeleteRelationState implements DataModel {

    String primaryName;
    String secondaryName;
    List<State> states;
    String basePackage;

    public DeleteRelationState(String primaryName, String secondaryName, List<State> states, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.states = states;
        this.basePackage = basePackage;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public List<State> getStates() {
        return states;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Delete" + primaryName + secondaryName + "State.java";
    }
}
