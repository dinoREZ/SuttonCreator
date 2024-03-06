package org.example.dataModels.api.states;

import org.example.State;
import org.example.dataModels.DataModel;

import javax.ws.rs.core.CacheControl;
import java.util.List;

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
}
