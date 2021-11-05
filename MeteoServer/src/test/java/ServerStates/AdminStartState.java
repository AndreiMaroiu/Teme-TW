package ServerStates;

import MeteoServer.City;
import Responses.Response;
import MeteoServer.ServerInfo;
import Responses.WriteResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public final class AdminStartState extends State
{
    private Response response = Response.Empty;

    public AdminStartState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public Response begin()
    {
        return new WriteResponse("What do you want to do? " +
                "([update cities] file or [view] cities or [log out])");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            String line = reader.readLine();

            switch (line)
            {
            case "view":
                var cities = ServerInfo.Instance.getCities();
                response = new WriteResponse(getCitiesOutput(cities));
                stateMachine.setState(new AdminStartState(stateMachine));
                break;
            case "update cities":
                stateMachine.setState(new AdminUpdateState(stateMachine));
                break;
            case "log out":
                stateMachine.setState(new LoginState(stateMachine));
                break;
            default:
                stateMachine.setState(new AdminStartState(stateMachine));
                break;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage() + " - thread is closing");
            stateMachine.close();
        }
    }

    @Override
    public Response getFinalResponse()
    {
        return response;
    }

    private String getCitiesOutput(List<City> cities)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("All cities:\n");

        for (var city : cities)
        {
            builder.append(city).append("\n");
        }

        return builder.toString();
    }
}
