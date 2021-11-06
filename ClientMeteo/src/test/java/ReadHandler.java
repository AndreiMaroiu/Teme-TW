import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadHandler
{
    private BufferedReader reader;
    private final Gson gson = new Gson();

    public ReadHandler(Socket socket)
    {
        try
        {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        }
        catch (Exception ex)
        {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String readResponse()
    {
        try
        {
            String line = reader.readLine();
            Response response = gson.fromJson(line, Response.class);
            System.out.println(response.getMessage());
            return response.getAction();
        }
        catch (Exception ex)
        {
            System.out.println("Error reading from server: " + ex.getMessage());
            ex.printStackTrace();
            return "";
        }
    }
}