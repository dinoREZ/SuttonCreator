package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import javax.ws.rs.core.CacheControl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetState implements DataModel {

    private String name;
    private boolean useEtags;
    private CacheControl cacheControl;
    private final List<State> states;
    private String basePackage;


    public GetState() {
        states = new ArrayList<>();
    }

    public GetState(String name, boolean useEtags, CacheControl cacheControl, List<State> states, String basePackage) {
        this.name = name;
        this.useEtags = useEtags;
        this.cacheControl = cacheControl;
        this.states = states;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUseEtags() {
        return useEtags;
    }

    public void setUseEtags(boolean useEtags) {
        this.useEtags = useEtags;
    }

    public CacheControl getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(CacheControl cacheControl) {
        this.cacheControl = cacheControl;
    }

    public List<State> getStates() {
        return states;
    }

    public void addState(State state) {
        states.add(state);
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public String getOutputName() {
        return "Get" + name + "State.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetState getState = (GetState) o;
        return useEtags == getState.useEtags && Objects.equals(name, getState.name) && Objects.equals(cacheControl, getState.cacheControl) && Objects.equals(states, getState.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, useEtags, cacheControl, states);
    }
}
