package MeteoServer;

public class City
{
    private String name;
    private Coordinates coordinates;
    private String weather;

    public City(String name, Coordinates coordinates, String weather)
    {
        this.name = name;
        this.coordinates = coordinates;
        this.weather = weather;
    }

    public String getName()
    {
        return name;
    }

    public Coordinates getCoordinates()
    {
        return coordinates;
    }

    public String getWeather()
    {
        return weather;
    }

    public void setWeather(String weather)
    {
        this.weather = weather;
    }

    @Override
    public String toString()
    {
        return name + " coordinates: " + coordinates.toString() + " weather: " + weather;
    }
}
