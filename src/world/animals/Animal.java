package world.animals;
import world.Organism;
import world.Direction;
import world.Point;
import world.WorldGrid;


public abstract class Animal extends Organism {

    Animal(world.Point cord, world.World where, int str, int ini){super(cord,where,str,ini);}

    @Override
    public boolean willItFend(Organism attacker){return false;}
    @Override
    public boolean willItEscape(){return false;}

    @Override
    public void  action () {
        world.takeFromBoard(this);
        world.Point last_cords = new Point( cords);
        Direction dir;
        do {
            if(world instanceof WorldGrid) dir = Direction.randomDirection();
            else dir = Direction.randomDirectionHex();
        } while (!willBeIn(dir));
        world.getCommentator().commentMovement(this,dir);
        Organism collision_target = move(dir);
        if (collision_target != null) Collision(collision_target,last_cords);
        if (world.checkIfAlive(this)) world.setOnBoard(this);
    }

    Organism move(Direction dir)
    {
        world.Point tmp = new Point(cords);
        dir.translate(tmp);
        Organism result=world.isThere(tmp);
        dir.translate(cords);
        return result;
    }

    boolean willBeIn(Direction dir)
    {
        world.Point tmp= new Point(cords);

        dir.translate(tmp);
        return tmp.x < world.getDimensions().x && tmp.y < world.getDimensions().y && tmp.x >= 0 && tmp.y >= 0;

    }


    void Collision(Organism collision_target, Point last_cords)
    {
        if(collision_target  instanceof Animal)
        {
            if(collision_target.getClass().equals(this.getClass()))
            {
                goBack(last_cords);
                world.setOnBoard(this);
                porliferation(collision_target);
                world.takeFromBoard(this);
            }
            else
            {
                if(collision_target.willItFend(this))goBack(last_cords);
                else fight(collision_target);
            }
        }
        else
        {
            world.getCommentator().commentEating(collision_target,this);
            ((world.plants.Plant)collision_target).beEaten(this);
        }

    }

    protected void goBack(Point lats_cords)
    {
        cords.x=lats_cords.x;
        cords.y=lats_cords.y;
    }

    void fight(Organism collision_target)
    {
        if(collision_target.willItEscape())return;
        world.getCommentator().commentFight(this,collision_target);
        if(strenght>=collision_target.getStrenght())
        {
            world.removeOrganism(collision_target);
            world.getCommentator().commentFightResult(this,true);
        }
        else
        {
            world.removeOrganism(this);
            if(world.checkIfAlive(collision_target))world.setOnBoard(collision_target);
            world.getCommentator().commentFightResult(this,false);
        }
    }


    void porliferation(Organism partner)
    {
        if(world.createInNeighbour(partner.getCords(),this.getClass()))
        {
            world.getCommentator().commentProliferation(this,partner,true);
            return;
        }
        if (world.createInNeighbour(cords, this.getClass()))
        {
            world.getCommentator().commentProliferation(this,partner,true);

            return;
        }
        world.getCommentator().commentProliferation(this,partner,false);

    }

}
