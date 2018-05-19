package world;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.sqrt;

public abstract class Organism {
    protected int strenght;
    protected int initiative;
    protected Point cords;
    protected World world;
    protected Image image;
    final protected Random gen = new Random();

    public Organism(Point cord, World where, int str, int ini) {
        strenght = str;
        initiative = ini;
        world = where;
        cords = cord;
    }

   public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int str) {
        strenght = str;
    }

    public int getInitiative() {
        return initiative;
    }

    public Point getCords() {
        return cords;
    }


        public abstract void action ();
        public abstract boolean willItFend(Organism attacker);
        public abstract boolean willItEscape();


        public void draw (Graphics g)
        {
            if(world instanceof WorldGrid) g.drawImage(image, cords.x * world.getFieldsize().x, cords.y * world.getFieldsize().y, world.getGamescreen());
            else {
                Point dimensions = world.getDimensions();
                int dim = dimensions.x > dimensions.y  ? dimensions.x : dimensions.y;
                dim++;
                if((cords.y-1)%2==1 || cords.y==0)
                g.drawImage(image, (cords.x *2 +1)* ((world.screensize.x  / (dim) / 2)) -(world.screensize.x  / (dim) / 2) ,
                        (int)((world.screensize.x / dim) + ((cords.y-1)*(3*(world.screensize.x / dim)/ 2 / sqrt(3))) + (world.screensize.x / dim)/ 2 / sqrt(3)), world.getGamescreen());
                else
                    g.drawImage(image, (cords.x *2 +1)* ((world.screensize.x  / (dim) / 2)),
                            (int)((world.screensize.x / dim) + ((cords.y-1)*(3*(world.screensize.x / dim)/ 2 / sqrt(3))) + (world.screensize.x / dim)/ 2 / sqrt(3)), world.getGamescreen());

            }

        }

}