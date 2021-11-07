package ServerStates;

import MeteoServer.*;
import Responses.Response;
import Responses.WriteResponse;

import java.io.BufferedReader;
import java.io.IOException;

public final class UserViewState extends State
{
    private static final String WRONG_INPUT_MESSAGE = "Please enter latitude and longitude to view a city";

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
            String line = reader.readLine();

            if (line == null || line.equals("") || line.equals("null"))
            {
                finalResponse = new WriteResponse(WRONG_INPUT_MESSAGE);
                stateMachine.setState(new UserViewState(stateMachine));
                return;
            }

            String[] items = line.split(" ");

            if (items.length < 2)
            {
                finalResponse = new WriteResponse(WRONG_INPUT_MESSAGE);
                stateMachine.setState(new UserViewState(stateMachine));
                return;
            }

            float lat = Float.parseFloat(items[0]);
            float lon = Float.parseFloat(items[1]);
            City city = ServerInfo.INSTANCE.getCity(new Coordinates(lat, lon));

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
