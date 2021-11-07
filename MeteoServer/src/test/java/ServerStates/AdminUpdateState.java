package ServerStates;

import MeteoServer.*;
import Responses.FileResponse;
import Responses.Response;
import Responses.WriteResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;

public class AdminUpdateState extends State
{
    private boolean isUpdated = false;

    public AdminUpdateState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public Response begin()
    {
        return new FileResponse("Enter full path to json:");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        Gson gson = new Gson();

        try
        {
            String input = reader.readLine();
            System.out.println("json input: " + input);

            if (input.equals("null") || input.equals(""))
            {
                stateMachine.setState(new AdminUpdateState(stateMachine));
                return;
            }

            ServerInfo.INSTANCE.update(gson.fromJson(input, City[].class));
            isUpdated = true;
            stateMachine.setState(new AdminStartState(stateMachine));
        }
        catch (JsonSyntaxException e)
        {
            System.out.println("Could not read json");
            stateMachine.setState(new AdminUpdateState(stateMachine));
        }
        catch (IOException e)
        {
            stateMachine.setState(new AdminUpdateState(stateMachine));
        }
    }

    @Override
    public Response getFinalResponse()
    {
        if (isUpdated)
        {
            return new WriteResponse("File updated!");
        }
        else
        {
            return new WriteResponse("Please enter a valid file!");
        }
    }
}
