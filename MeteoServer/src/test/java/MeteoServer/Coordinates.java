package MeteoServer;

public class Coordinates
{
    private int latitude;
    private int longitude;

    public Coordinates(int x, int y)
    {
        this.latitude = x;
        this.longitude = y;
    }

    public int getLatitude()
    {
        return latitude;
    }

    public int getLongitude()
    {
        return longitude;
    }

    public void setLatitude(int latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude)
    {
        this.longitude = longitude;
    }

    public Coordinates minus(Coordinates other)
    {
        return new Coordinates(latitude - other.latitude, longitude - other.longitude);
    }

    public int sqrtMagnitude()
    {
        return (latitude * latitude) + (longitude * longitude);
    }

    @Override
    public String toString()
    {
        return latitude + " " + longitude;
    }
}
