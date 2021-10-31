import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public final class Server
{
    private final int port;
    private final Set<ClientThread> clientThreads = new HashSet<>();
    private final ServerInfo serverInfo;

    public Server(int port)
    {
        this.port = port;

        serverInfo = new ServerInfo();
    }

    public void execute()
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Meteo Server is open on port " + port);

            while (true)
            {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                ClientThread newUser = new ClientThread(socket, this);
                clientThreads.add(newUser);
                newUser.start();
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}