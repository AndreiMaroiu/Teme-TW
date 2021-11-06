package ServerStates;

import Responses.Response;
import Responses.WriteResponse;

import java.io.BufferedReader;
import java.io.IOException;

public final class LoginState extends State
{
    private Response finalResponse = Response.Empty;

    public LoginState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public Response begin()
    {
        return new WriteResponse("Enter your role ([user] or [admin]):");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            String userType = reader.readLine();

            switch (userType)
            {
            case "admin":
                System.out.println("New admin connected!");
                finalResponse = new WriteResponse("You are now logged in as: " + userType);
                stateMachine.setState(new AdminStartState(stateMachine));
                break;
            case "user":
                System.out.println("New user connected!");
                finalResponse = new WriteResponse("You are now logged in as: " + userType);
                stateMachine.setState(new UserStartState(stateMachine));
                break;
            default:
                finalResponse = new WriteResponse("Try to log as an admin or user!");
                stateMachine.setState(new LoginState(stateMachine));
                break;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            stateMachine.close();
        }
    }

    @Override
    public Response getFinalResponse()
    {
        return finalResponse;
    }
}
