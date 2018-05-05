package world.animals;
import world.Organism;
import world.Direction;
import world.WorldGrid;

import java.awt.*;
import java.util.Random;

public abstract class Animal extends Organism {

    Animal(world.Point cord, world.World where, int str, int ini){super(cord,where,str,ini);}

    @Override
    public boolean willItFend(Organism attacker){return false;}
    @Override
    public boolean willItEscape(){return true;}

    @Override
    public void  action () {
        world.takeFromBoard(this);
        Random gen = new Random();
        world.Point last_cords = cords;
        Direction dir;
        do {
            dir = Direction.randomDirection();

        } while (!willBeIn(dir));

        Organism collision_target = move(dir);
        if (collision_target != null) ;
        if (world.checkIfAlive(this)) world.setOnBoard(this);
    }

    Organism move(Direction dir)
    {
        world.Point tmp = cords;
        dir.translate(tmp);
        Organism result=world.isThere(tmp);
        dir.translate(cords);
        return  result;
    }

    boolean willBeIn(Direction dir)
    {
        world.Point tmp=cords;

        dir.translate(cords);
        if(tmp.x >= world.getDimensions().x || tmp.y >= world.getDimensions().y || tmp.x<0 || tmp.y<0)return false;
        return true;

    }




}
