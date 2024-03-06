package org.example.dataModels.api.states;

import org.example.Resource;
import org.example.State;
import org.example.dataModels.DataModel;

import java.util.*;

public class DeleteState implements DataModel {

    private String name;
    private final List<State> states;
    private final List<Resource> resources;
    private String basePackage;


    public DeleteState() {
        states = new ArrayList<>();
        resources = new ArrayList<>();
    }

    public DeleteState(String name, List<State> states, List<Resource> resources, String basePackage) {
        this.name = name;
        this.states = states;
        this.resources = resources;
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

    public List<Resource> getResources() {
        return resources;
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
