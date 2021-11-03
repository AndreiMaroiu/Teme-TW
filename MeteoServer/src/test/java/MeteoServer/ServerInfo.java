package MeteoServer;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public final class ServerInfo
{
    private static final String FILE_PATH = "cities.json";

    private final ArrayList<City> cities = new ArrayList<>();

    public ServerInfo()
    {
        read(FILE_PATH);
    }

    public ServerInfo(String filePath)
    {
        update(filePath);
    }

    public void update(String filePath)
    {
        read(filePath);
        saveToFile(FILE_PATH);
    }

    public synchronized ArrayList<City> getCities()
    {
        return new ArrayList<>(cities);
    }

    public City getCity(Vector2 coordinates)
    {
        int minDistance = Integer.MAX_VALUE;
        City result = null;

        synchronized(this)
        {
            for (City city : cities)
            {
                int distance = city.getCoordinates().minus(coordinates).sqrtMagnitude();

                if (distance < minDistance)
                {
                    result = city;
                    minDistance = distance;
                }
            }
        }

        return result;
    }

    public synchronized void read(String filePath)
    {
        cities.clear();

        try
        {
            Gson gson = new Gson();
            String content = Files.readString(Path.of(filePath));
            City[] list = gson.fromJson(content, City[].class);

            for (City city : list)
            {
                cities.add(city);
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void saveToFile(String filePath)
    {
        try (FileWriter writer = new FileWriter(filePath)){
            Gson gson = new Gson();
            synchronized (this) {
                gson.toJson(cities, writer);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
