package world.plants;
import world.Organism;

public abstract class Plant extends Organism {
    Plant(world.Point cord, world.World where, int str, int ini){super(cord,where,str,ini);}
    public void Action() {};
    public void Draw(){}
    public boolean Will_it_fend (Organism attacker){return false;};
    public boolean Will_it_escape (){return false;};

}
