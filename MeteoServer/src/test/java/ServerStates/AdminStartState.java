package ServerStates;

import MeteoServer.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public final class AdminStartState extends State
{
    public AdminStartState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public void begin()
    {
        stateMachine.getWriter().println("What do you want to do? " +
                "(Update city weather or update cities file or view cities)");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            String line = reader.readLine();

            // todo: handle data

            if (line.equals("view"))
            {
                var cities = stateMachine.getServer().getServerInfo().getCities();

                stateMachine.getWriter().println(getCitiesOutput(cities).toString());

                stateMachine.setState(new AdminStartState(stateMachine));
            }
            else if (line.equals("update city"))
            {
                stateMachine.setState(new AdminUpdateState(stateMachine));
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage() + " - thread is closing");
            stateMachine.close();
        }
    }

    private String getCitiesOutput(ArrayList<City> cities)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("All cities:\n");

        for (var city : cities)
        {
            builder.append(city + "\n");
        }

        return builder.toString();
    }
}
