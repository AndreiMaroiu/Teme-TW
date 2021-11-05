import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Client
{
    private final String hostname;
    private final int port;

    public Client(String hostname, int port)
    {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute()
    {
        try
        {
            run();
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Server not found: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println("I/O Error: " + ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println("Exception:" + ex.getMessage());
        }
    }

    private void run() throws IOException
    {
        Socket socket = new Socket(hostname, port);
        System.out.println("Connected to the meteo server. Enter [bye] to close client");

        new WriteThread(socket).start();
    }
}
