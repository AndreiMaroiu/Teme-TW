package ServerStates;

import java.io.PrintWriter;

public final class StateMachine
{
    private final PrintWriter writer;
    private State state;
    private boolean canClose = false;

    public StateMachine(PrintWriter writer)
    {
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

    public void close()
    {
        canClose = true;
    }

    public boolean canClose() {
        return canClose;
    }
}
