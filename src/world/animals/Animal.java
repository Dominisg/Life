package world.animals;
import world.Organism;

public abstract class Animal extends Organism {
    Animal(world.Point cord, world.World where, int str, int ini){super(cord,where,str,ini);}
    public void  Action (){};
    public boolean Will_it_fend (Organism attacker){return false;};
    public boolean Will_it_escape (){return true;};
    public void Draw (){};

}
