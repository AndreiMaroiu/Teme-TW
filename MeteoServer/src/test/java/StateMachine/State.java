package StateMachine;

import java.io.BufferedReader;

public abstract class State
{
    protected StateMachine stateMachine;

    public State(StateMachine stateMachine)
    {
        this.stateMachine = stateMachine;
    }

    public abstract void begin();
    public abstract void readData(BufferedReader reader);
}
