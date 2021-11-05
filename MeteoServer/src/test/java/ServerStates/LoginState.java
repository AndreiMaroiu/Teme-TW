package ServerStates;

import Responses.Response;
import Responses.WriteResponse;

import java.io.BufferedReader;
import java.io.IOException;

public final class LoginState extends State
{
    private String userType;
    private Response finalResponse = Response.Empty;

    public LoginState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public Response begin()
    {
        return new WriteResponse("Enter your role:");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            userType = reader.readLine();

            switch (userType)
            {
            case "admin":
                stateMachine.setState(new AdminStartState(stateMachine));
                System.out.println("New admin connected!");
                finalResponse = new WriteResponse("You are now logged in as: " + userType);
                break;
            case "user":
                stateMachine.setState(new UserStartState(stateMachine));
                System.out.println("New user connected!");
                finalResponse = new WriteResponse("You are now logged in as: " + userType);
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
            e.printStackTrace();
            stateMachine.close();
        }
    }

    @Override
    public Response getFinalResponse()
    {
        return finalResponse;
    }
}
