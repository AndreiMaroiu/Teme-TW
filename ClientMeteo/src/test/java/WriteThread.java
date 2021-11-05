import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteThread extends Thread
{
    private final ReadHandler readHandler;
    private final Socket socket;
    private PrintWriter socketWriter;

    public WriteThread(Socket socket)
    {
        this.socket = socket;
        readHandler = new ReadHandler(socket);

        try
        {
            OutputStream output = socket.getOutputStream();
            socketWriter = new PrintWriter(output, true);
        }
        catch (Exception ex)
        {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String readLine()
    {
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
    public void run()
    {
        try
        {
            String input;

            do
            {
                String action = readHandler.readResponse();
                input = readLine();
                socketWriter.println(getOutput(input, action));
                readHandler.readResponse();
            } while (!input.equals("bye"));

            socket.close();
        }
        catch (IOException ex)
        {
            System.out.println("Error writing or reading to server: " + ex.getMessage());
            System.out.println("Client closing");
        }
    }

    private String getOutput(String input, String action)
    {
        String output = null;

        if (action.equals("write"))
        {
            output = input;
        }
        else if (action.equals("readFile"))
        {
            try
            {
                Path path = Path.of(input);
                String content = Files.readString(path);
                output = content.trim().replace("\n", "").replace("\r", "");
            }
            catch (IOException e)
            {
                System.out.println("error reading the file");
                System.out.println(e.getMessage());
            }
        }

        return output;
    }
}