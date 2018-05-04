package world;

import java.util.Deque;
import java.util.Vector;

 public abstract class World {

     Organism[][] map;
     Point dimensions;
     Vector<Organism> organisms;
     Vector<Organism> created_list;
     Vector<Organism> destroy_list;

     World(Point dim) {
         map = new Organism[dim.x][dim.y];
     }



     abstract public void Draw();


 }
