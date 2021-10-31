public class Vector2
{
    private int x;
    private int y;

    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Vector2 minus(Vector2 other)
    {
        return new Vector2(x - other.x, y - other.y);
    }

    public int sqrtMagnitude()
    {
        return (x * x) + (y * y);
    }

    @Override
    public String toString()
    {
        return x + " " + y;
    }
}
