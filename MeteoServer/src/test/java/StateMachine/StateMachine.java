package StateMachine;

import java.io.PrintWriter;

public final class StateMachine
{
    private final PrintWriter writer;
    private State state;

    public StateMachine(State state, PrintWriter writer)
    {
        setState(state);
        this.writer = writer;
    }

    public void setState(State state)
    {
        this.state = state;
        state.begin();
    }

    public State getState()
    {
       return state;
    }

    public PrintWriter getWriter()
    {
        return writer;
    }
}
