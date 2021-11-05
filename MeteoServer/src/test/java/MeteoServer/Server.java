package MeteoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public final class Server
{
    private final int port;
    private final Set<ClientThread> clientThreads = new HashSet<>();

    public Server(int port)
    {
        this.port = port;
        ServerInfo.Instance.init();
    }

    public void execute()
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Meteo server is now open on port " + port);

            while (true)
            {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

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