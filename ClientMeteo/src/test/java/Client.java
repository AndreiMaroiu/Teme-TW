import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Client
{
    private String hostname;
    private int port;
    private String userName;
    private Socket socket;
    private ReadThread readThread;

    public Client(String hostname, int port)
    {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute()
    {
        try
        {
            socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");

            new WriteThread(socket, this).start();
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

    public void startReadThread()
    {
        readThread = new ReadThread(socket, this);
        readThread.start();
    }

    public void stop()
    {
        readThread.stopConnection();
    }

    void setUserName(String userName)
    {
        this.userName = userName;
    }

    String getUserName()
    {
        return this.userName;
    }
}
