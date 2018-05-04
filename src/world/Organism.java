package world;

public abstract class Organism {
    int strenght;
    int initiative;
    Point cords;
    World world;

    public Organism(Point cord, World where, int str, int ini) {
        strenght = str;
        initiative = ini;
        world = where;
        cords = cord;
    }

   public int GetStrenght() {
        return strenght;
    }

    public void SetStrenght(int str) {
        strenght = str;
    }

    public int GetInitiative() {
        return initiative;
    }

    public Point GetCords() {
        return cords;
    }


        public abstract void Action ();
        public abstract boolean Will_it_fend (Organism attacker);
        public abstract boolean Will_it_escape ();
        public abstract void Draw ();

}