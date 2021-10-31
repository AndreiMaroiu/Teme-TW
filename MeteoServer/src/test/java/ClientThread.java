import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread
{
    private Socket socket;
    private Server server;
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

            String userType = getUserType();
            System.out.println("new " + userType + " has logged in.");
            String clientMessage;

            do
            {
                clientMessage = reader.readLine();
                // TODO handle data

                if(userType.equals("admin"))
                {
                    handleAdminInput(clientMessage);
                }
                else if(userType.equals("user"))
                {
                    handleUserInput(clientMessage);
                }

            } while (!clientMessage.equals("close"));

            // TODO server remove user if necessary
            socket.close();
        }
        catch(SocketException ex)
        {
            System.out.println("Lost connection to user");
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

    private String getUserType() throws IOException
    {
        String userType;

        while(true)
        {
            userType = reader.readLine();

            if (userType.equals("admin") || userType.equals("user"))
            {
                break;
            }
            else
            {
                sendMessage("Try to log as a user or admin");
            }
        }

        return userType;
    }

    private void handleUserInput(String input)
    {

    }

    private void handleAdminInput(String input)
    {

    }

    /**
     * Sends a message to the client.
     */
    void sendMessage(String message)
    {
        writer.println(message);
    }
}