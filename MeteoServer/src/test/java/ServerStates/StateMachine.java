package ServerStates;

import Responses.Response;
import MeteoServer.Server;
import com.google.gson.Gson;

import java.io.PrintWriter;

public final class StateMachine
{
    private final PrintWriter writer;
    private State state = null;
    private boolean canClose = false;
    private final Gson gson = new Gson();

    public StateMachine(PrintWriter writer)
    {
        this.writer = writer;
    }

    public void setState(State newState)
    {
        if (this.state != null)
        {
            sendResponse(state.getFinalResponse());
        }

        state = newState;
        sendResponse(state.begin());
    }

    private void sendResponse(Response response)
    {
        String message = gson.toJson(response);

        writer.println(message);
    }

    public State getState()
    {
       return state;
    }

    public void close()
    {
        canClose = true;
    }

    public boolean canClose() {
        return canClose;
    }
}
