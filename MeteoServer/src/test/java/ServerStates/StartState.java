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
        stateMachine.getWriter().println("enter your role:");
    }

    @Override
    public void readData(BufferedReader reader)
    {
        try
        {
            String userType = getUserType(reader);

            if (userType.equals("admin"))
            {
                stateMachine.setState(new AdminStartState(stateMachine));
            }
            else if (userType.equals("user"))
            {
                stateMachine.setState(new UserStartState(stateMachine));
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private String getUserType(BufferedReader reader) throws IOException
    {
        String userType;

        while(true)
        {
            userType = reader.readLine();

            if (userType.equals("admin") || userType.equals("user"))
            {
                System.out.println(userType + " Connected");
                break;
            }
            else
            {
                stateMachine.getWriter().println("Try to log as a user or admin");
            }
        }

        return userType;
    }
}
