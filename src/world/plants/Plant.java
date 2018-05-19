package world.plants;
import world.Organism;

import java.util.Random;


public abstract class Plant extends Organism {
    final protected int probability=10;
    Plant(world.Point cord, world.World where, int str, int ini){super(cord,where,str,ini);}
    public void action() {
        spread();
    }

    public boolean willItFend (Organism attacker){return false;}
    public boolean willItEscape (){return false;}
    public void beEaten(Organism organism) {
        world.removeOrganism(this);
    }
    public boolean spread()
    {
        if(((gen.nextInt()&Integer.MAX_VALUE) % 100 +1)<= probability)
        {
            if( world.createInNeighbour(cords, getClass())){
                world.getCommentator().commentSpreading(this);
                return true;
            }
        }
        return false;
    }


}


