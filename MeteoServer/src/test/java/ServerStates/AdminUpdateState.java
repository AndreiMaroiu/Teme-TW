package ServerStates;

import java.io.BufferedReader;

public class AdminUpdateState extends State
{
    public AdminUpdateState(StateMachine stateMachine){
        super(stateMachine);
    }

    @Override
    public void begin() {
        stateMachine.getWriter().println("update state");
    }

    @Override
    public void readData(BufferedReader reader) {

    }
}
