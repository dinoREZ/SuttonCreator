package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

import javax.ws.rs.core.CacheControl;
import java.util.Objects;

public class GetState implements DataModel {

    private String name;
    private boolean useEtags;
    private CacheControl cacheControl;
    private String basePackage;


    public GetState() {
    }

    public GetState(String name, boolean useEtags, CacheControl cacheControl, String basePackage) {
        this.name = name;
        this.useEtags = useEtags;
        this.cacheControl = cacheControl;
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
        return useEtags == getState.useEtags && Objects.equals(name, getState.name) && Objects.equals(cacheControl, getState.cacheControl) && Objects.equals(basePackage, getState.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, useEtags, cacheControl, basePackage);
    }
}
