package ServerStates;

import java.io.BufferedReader;
import java.io.IOException;

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

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage() + " - thread is closing");
            stateMachine.close();
        }
    }
}
