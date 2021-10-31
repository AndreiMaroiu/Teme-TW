import java.io.*;
import java.net.Socket;

public class WriteThread extends Thread {
    private PrintWriter socketWriter;
    private Socket socket;
    private Client client;

    public WriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            socketWriter = new PrintWriter(output,true);
        } catch (Exception ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String readLine(String message)
    {
        System.out.print(message);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            return reader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void run() {
        String userName = readLine("\nEnter your role: ");
        client.setUserName(userName);
        socketWriter.println(userName);

        client.startReadThread();

        String text;
        do {
            text = readLine("");
            socketWriter.println(text);
        } while (!text.equals("bye"));
        client.stop();

        try {
            socket.close();
        } catch (Exception ex) {
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}