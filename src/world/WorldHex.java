package world;

public class WorldHex extends World{


    public WorldHex(Point dim){
        super(dim);
    }

    @Override
    public boolean createInNeighbour(Point point, Class type){return true;}
}
