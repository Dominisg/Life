package world.plants;
import world.Organism;

public abstract class Plant extends Organism {
    Plant(world.Point cord, world.World where, int str, int ini){super(cord,where,str,ini);}
    public void action() {};
    public boolean willItFend (Organism attacker){return false;}
    public boolean willItEscape (){return false;}

}
