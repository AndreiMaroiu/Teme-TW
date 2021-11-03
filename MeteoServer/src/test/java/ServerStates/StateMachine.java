package ServerStates;

import MeteoServer.Server;

import java.io.PrintWriter;

public final class StateMachine
{
    private final PrintWriter writer;
    private State state;
    private boolean canClose = false;
    private final Server server;

    public StateMachine(PrintWriter writer, Server server)
    {
        this.writer = writer;
        this.server = server;
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

    public Server getServer()
    {
        return server;
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
