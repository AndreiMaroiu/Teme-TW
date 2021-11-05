package ServerStates;

import Responses.Response;
import Responses.WriteResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class UserStartState extends State
{
    public UserStartState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public Response begin()
    {
        return new WriteResponse("What do you want to do ([view city] or [log out])");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            String line = reader.readLine();

            switch (line)
            {
            case "view city":
                stateMachine.setState(new UserViewState(stateMachine));
                break;
            case "log out":
                stateMachine.setState(new LoginState(stateMachine));
                break;
            default:
                stateMachine.setState(new UserStartState(stateMachine));
                break;
            }
        }
        catch (IOException e)
        {
            System.out.println("Could not read from client. Thread stopping.");
            stateMachine.close();
        }
    }

    @Override
    public Response getFinalResponse()
    {
        return Response.Empty;
    }
}
