package MeteoServer;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public enum ServerInfo
{
    Instance;

    private static final String FILE_PATH = "cities.json";

    private final List<City> cities = new Vector<>();

    public void init()
    {
        read(FILE_PATH);
    }

    public synchronized void update(City[] list)
    {
        cities.clear();

        for (var city: list)
        {
            cities.add(city);
        }

        saveToFile(FILE_PATH);

        System.out.println("Server info updated!");
    }

    public synchronized List<City> getCities()
    {
        return new Vector<>(cities);
    }

    public City getCity(Coordinates coordinates)
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
