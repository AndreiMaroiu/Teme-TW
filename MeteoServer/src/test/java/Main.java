import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        int port = 8080;

        Server server = new Server(port);
        server.execute();
    }
}
