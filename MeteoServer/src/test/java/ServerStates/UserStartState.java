package ServerStates;

import MeteoServer.City;
import MeteoServer.Vector2;

import java.io.BufferedReader;
import java.io.IOException;

public final class UserStartState extends State
{
    public UserStartState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public void begin() {
        stateMachine.getWriter().println("Please enter a city coordinates (latitude and longitude)!");
    }

    @Override
    public void readData(BufferedReader reader) {
        try
        {
            int lat = Integer.parseInt(reader.readLine());
            int lon = Integer.parseInt(reader.readLine());
            City city = stateMachine.getServer().getServerInfo().getCity(new Vector2(lat, lon));

            stateMachine.getWriter().println("Weather in " + city.getName() + " is " + city.getWeather());
            stateMachine.setState(new UserStartState(stateMachine));
        }
        catch (IOException e)
        {
            stateMachine.close();
            e.printStackTrace();
        }
    }
}
