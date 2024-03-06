package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import javax.ws.rs.core.CacheControl;
import java.util.List;
import java.util.Objects;

public class GetRelationState implements DataModel {

    String primaryName;
    String secondaryName;
    boolean useEtags;
    CacheControl cacheControl;
    List<State> states;
    String basePackage;

    public GetRelationState(String primaryName, String secondaryName, boolean useEtags, CacheControl cacheControl, List<State> states, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.useEtags = useEtags;
        this.cacheControl = cacheControl;
        this.states = states;
        this.basePackage = basePackage;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public boolean isUseEtags() {
        return useEtags;
    }

    public CacheControl getCacheControl() {
        return cacheControl;
    }

    public List<State> getStates() {
        return states;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Get" + primaryName + secondaryName + "State.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetRelationState that = (GetRelationState) o;
        return useEtags == that.useEtags && Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(cacheControl, that.cacheControl) && Objects.equals(states, that.states) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, useEtags, cacheControl, states, basePackage);
    }
}
