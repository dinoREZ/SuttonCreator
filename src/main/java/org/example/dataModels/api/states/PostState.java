package org.example.dataModels.api.states;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class PostState implements DataModel {

    String name;
    String basePackage;

    public PostState(String name, String basePackage) {
        this.name = name;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return "Post" + name + "State.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostState postState = (PostState) o;
        return Objects.equals(name, postState.name) && Objects.equals(basePackage, postState.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePackage);
    }
}
