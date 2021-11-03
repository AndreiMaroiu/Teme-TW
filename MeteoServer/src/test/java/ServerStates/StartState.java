package ServerStates;

import java.io.BufferedReader;
import java.io.IOException;

public final class StartState extends State
{
    private String readData;

    public StartState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public void begin()
    {
        stateMachine.getWriter().println("Enter your role:");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            String userType = reader.readLine();

            if (userType.equals("admin"))
            {
                stateMachine.setState(new AdminStartState(stateMachine));
                System.out.println("New client connected!");
            }
            else if (userType.equals("user"))
            {
                stateMachine.setState(new UserStartState(stateMachine));
                System.out.println("New client connected!");
            }
            else
            {
                stateMachine.getWriter().println("Try to log as an admin or user!");
                stateMachine.setState(new StartState(stateMachine));
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            stateMachine.close();
        }
    }
}
