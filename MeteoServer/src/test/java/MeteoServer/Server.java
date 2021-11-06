package MeteoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Server
{
    private static final int MAX_THREADS = 10;

    private final int port;

    public Server(int port)
    {
        this.port = port;
        ServerInfo.Instance.init();
    }

    public void execute()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);

        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Meteo server is now open on port " + port);

            while (true)
            {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                ClientThread newUser = new ClientThread(socket, this);
                executorService.execute(newUser);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}