import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public final class Server
{
    private final int port;
    private final Set<String> userNames = new HashSet<>();
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
            System.out.println("Chat Server is listening on port " + port);

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

    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message)
    {
        for (ClientThread user : clientThreads)
        {
            user.sendMessage(message);
        }
    }

    /**
     * Stores username of the newly connected client.
     */
    void addUserName(String userName)
    {
        userNames.add(userName);
    }

    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removeUser(String userName, ClientThread user)
    {
        boolean removed = userNames.remove(userName);

        if (removed)
        {
            clientThreads.remove(user);
            System.out.println("The user " + userName + " quitted");
        }
    }

    Set<String> getUserNames()
    {
        return userNames;
    }

    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    boolean hasUsers()
    {
        return !userNames.isEmpty();
    }
}