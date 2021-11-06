package MeteoServer;

import ServerStates.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread
{
    private final Server server;
    private final Socket socket;

    public ClientThread(Socket socket, Server server)
    {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            StateMachine stateMachine = new StateMachine(writer);
            stateMachine.setState(new LoginState(stateMachine));

            do
            {
                stateMachine.getState().readData(reader);
            } while (!stateMachine.canClose());

            socket.close();
        }
        catch(SocketException ex)
        {
            System.out.println("Lost connection to user");
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}