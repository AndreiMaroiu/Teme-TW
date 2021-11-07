package MeteoServer;

public class Coordinates
{
    private final float latitude;
    private final float longitude;

    public Coordinates(float x, float y)
    {
        this.latitude = x;
        this.longitude = y;
    }

    public float getLatitude()
    {
        return latitude;
    }

    public float getLongitude()
    {
        return longitude;
    }

    public Coordinates minus(Coordinates other)
    {
        return new Coordinates(latitude - other.latitude, longitude - other.longitude);
    }

    public float sqrLength()
    {
        return (latitude * latitude) + (longitude * longitude);
    }

    @Override
    public String toString()
    {
        return latitude + " " + longitude;
    }
}
