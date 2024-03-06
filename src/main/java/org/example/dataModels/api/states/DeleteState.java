package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import javax.ws.rs.core.CacheControl;
import java.util.*;

public class DeleteState implements DataModel {

    private String name;
    private final List<State> states;
    private final Map<String, List<String>> subResources;
    private String basePackage;


    public DeleteState() {
        states = new ArrayList<>();
        subResources = new HashMap<>();
    }

    public DeleteState(String name, List<State> states, Map<String, List<String>> subResources, String basePackage) {
        this.name = name;
        this.states = states;
        this.subResources = subResources;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<State> getStates() {
        return states;
    }

    public void addState(State state) {
        states.add(state);
    }

    public Map<String, List<String>> getSubResources() {
        return subResources;
    }


    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public String getOutputName() {
        return "Delete" + name + "State.java";
    }
}
