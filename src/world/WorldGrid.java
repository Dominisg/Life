package world;

public class WorldGrid extends World {
    public WorldGrid(Point dim){
        super(dim);
    }

    @Override
    public boolean createInNeighbour(Point point, Class type){
        Point tmp = new Point(point);
        tmp.x++;
        if((isThere(tmp)==null) && tmp.x<dimensions.x)
        {
            addToQueue(type,tmp);
            return true;
        }
        tmp.x-=2;
        if((isThere(tmp)==null) && tmp.x>=0)
        {
            addToQueue(type,tmp);
            return true;
        }
        tmp.x++;
        tmp.y++;
        if((isThere(tmp)==null) && tmp.y<dimensions.y)
        {
            addToQueue(type,tmp);
            return true;
        }
        tmp.y-=2;
        if((isThere(tmp)==null) && tmp.y>=0)
        {
            addToQueue(type,tmp);
            return true;
        }
        return false;
    }
}
