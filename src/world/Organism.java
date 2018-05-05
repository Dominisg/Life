package world;

import java.awt.*;

public abstract class Organism {
    protected int strenght;
    protected int initiative;
    protected Point cords;
    protected World world;
    protected Image image;

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

            g.drawImage(image, cords.x * world.getFieldsize().x, cords.y * world.getFieldsize().y, world.getGamescreen());

        }

}