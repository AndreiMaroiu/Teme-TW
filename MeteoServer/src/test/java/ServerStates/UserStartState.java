package ServerStates;

import java.io.BufferedReader;

public final class UserStartState extends State
{
    public UserStartState(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    @Override
    public void begin() {
        stateMachine.getWriter().println("Hello user!");
    }

    @Override
    public void readData(BufferedReader reader) {

    }
}
