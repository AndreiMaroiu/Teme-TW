package ServerStates;

import Responses.Response;

import java.io.BufferedReader;

public abstract class State
{
    protected StateMachine stateMachine;

    public State(StateMachine stateMachine)
    {
        this.stateMachine = stateMachine;
    }

    public abstract Response begin();
    public abstract void readData(BufferedReader reader);
    public abstract Response getFinalResponse();
}
