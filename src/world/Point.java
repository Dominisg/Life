package world;

public class Point {
    public int x;
    public int y;

    public Point (Point copied)
    {
        this.x=copied.x;
        this.y=copied.y;
    }

   public Point (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(){};
}
