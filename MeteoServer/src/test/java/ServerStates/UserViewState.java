package ServerStates;

import MeteoServer.*;
import Responses.Response;
import Responses.WriteResponse;

import java.io.BufferedReader;
import java.io.IOException;

public final class UserViewState extends State
{
    private Response finalResponse = Response.Empty;

    public UserViewState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public Response begin()
    {
        return new WriteResponse("Please enter a city coordinates ([latitude] [longitude])!");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            String[] items = reader.readLine().split(" ");

            if (items.length < 2)
            {
                stateMachine.setState(new UserViewState(stateMachine));
                return;
            }

            int lat = Integer.parseInt(items[0]);
            int lon = Integer.parseInt(items[1]);
            City city = ServerInfo.Instance.getCity(new Coordinates(lat, lon));

            finalResponse = new WriteResponse("Weather in " + city.getName() + " is " + city.getWeather());
            stateMachine.setState(new UserStartState(stateMachine));
        }
        catch (NumberFormatException e)
        {
            finalResponse = new WriteResponse("Wrong number format.");
            stateMachine.setState(new UserViewState(stateMachine));
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
        return finalResponse;
    }
}
