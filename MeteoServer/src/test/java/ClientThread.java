import ServerStates.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread
{
    private final Server server;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

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
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            StateMachine stateMachine = new StateMachine(writer);
            stateMachine.setState(new StartState(stateMachine));

            do
            {
                stateMachine.getState().readData(reader);
            } while (!stateMachine.canClose());

            // TODO server remove user if necessary
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

    /**
     * Sends a message to the client.
     */
    public void sendMessage(String message)
    {
        writer.println(message);
    }
}