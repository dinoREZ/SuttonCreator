package org.example;

public class State {

    StateType stateType;

    public StateType getStateType() {
        return stateType;
    }

    public State setStateType(StateType stateType) {
        this.stateType = stateType;
        return this;
    }
}
