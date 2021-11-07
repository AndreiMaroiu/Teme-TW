package MeteoServer;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public enum ServerInfo
{
    INSTANCE();

    private static final String FILE_PATH = "cities.json";
    private final List<City> cities = new Vector<>();

    ServerInfo()
    {
        read();
    }

    public synchronized void update(City[] list)
    {
        cities.clear();
        Collections.addAll(cities, list);
        saveToFile();

        System.out.println("Server info updated!");
    }

    public List<City> getCities()
    {
        return cities;
    }

    public City getCity(Coordinates coordinates)
    {
        float minDistance = Float.MAX_VALUE;
        City result = null;

        synchronized(this)
        {
            for (City city : cities)
            {
                float distance = city.getCoordinates().minus(coordinates).sqrLength();
                if (distance < minDistance)
                {
                    result = city;
                    minDistance = distance;
                }
            }
        }

        return result;
    }

    public synchronized void read()
    {
        cities.clear();

        try
        {
            Gson gson = new Gson();
            String content = Files.readString(Path.of(FILE_PATH));
            City[] list = gson.fromJson(content, City[].class);

            Collections.addAll(cities, list);
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private void saveToFile()
    {
        try (FileWriter writer = new FileWriter(ServerInfo.FILE_PATH))
        {
            Gson gson = new Gson();
            synchronized (this)
            {
                gson.toJson(cities, writer);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
