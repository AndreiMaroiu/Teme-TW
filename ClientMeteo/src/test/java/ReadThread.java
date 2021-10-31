import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread
{
    private BufferedReader reader;
    private Socket socket;
    private Client client;
    private boolean isRunning;

    public ReadThread(Socket socket, Client client)
    {
        this.socket = socket;
        this.client = client;

        try
        {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            isRunning = true;
        }
        catch (Exception ex)
        {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        try
        {
            while (isRunning)
            {
                String response = reader.readLine();
                System.out.println("\n" + response);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error reading from server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void stopConnection()
    {
        isRunning = false;
    }
}