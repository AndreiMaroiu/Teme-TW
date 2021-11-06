package MeteoServer;

public class City
{
    private final String name;
    private final Coordinates coordinates;
    private final String weather;

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

    @Override
    public String toString()
    {
        return name + " coordinates: " + coordinates.toString() + " weather: " + weather;
    }
}
