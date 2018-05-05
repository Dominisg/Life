package world;


import world.gui.GameScreen;
import java.util.Deque;
import java.util.Random;
import java.util.Vector;

 public abstract class World {

     final private Point screensize = new Point(600,800);
     private Organism[][] map;
     private Point dimensions;
     private GameScreen gamescreen;
     private Vector<Organism> organisms = new Vector<>();
     private Deque<Organism> created_list;
     private Deque<Organism> destroy_list;

     
     
     World(Point dim) {
         map = new Organism[dim.x][dim.y];
         dimensions = dim;
     }

    public Vector<Organism> getOrganisms()
     {
         return organisms;
     }
    public Deque <Organism> getRecentlyCreatedOrganisms()
     {
         return created_list;
     }

     void createStartingBoard()
     {
        createOrganism(world.animals.Sheep.class);
        createOrganism(world.plants.Grass.class);
     }

     void performRound()
     {
         for (Organism organism : organisms)
         {
             if(checkIfAlive(organism))organism.action();
         }
         destroyOrganisms();
         gamescreen.repaint();
     }

     public void setGamescreen(GameScreen gs)
     {
         gamescreen=gs;
     }
     public GameScreen getGamescreen()
     {
         return gamescreen;
     }

     public Point getDimensions() {
         return dimensions;
     }

     public Point getFieldsize() {
         return new Point(screensize.x/dimensions.x,(screensize.y-200)/dimensions.y);

     }

     private void bubbleOrganism() {
         int size = organisms.size();

         for (int j = 0; j < size - 1; j++) {
             if (organisms.get(size - j - 1).getInitiative() > organisms.get(size - j - 2).getInitiative()) {
                 Organism tmp = organisms.get(size - j - 1);
                 organisms.set(size - j - 1, organisms.get(size - j - 2));
                 organisms.set(size - j - 2, tmp);
             }
         }

     }

     public Organism isThere(Point cords) {
         if (cords.x >= dimensions.x || cords.y >= dimensions.y || cords.y < 0 || cords.x < 0) return null;
         return map[cords.x][cords.y];
     }

     void addCreatedOrganism() {
         while (created_list.size() > 0) {
             setOnBoard(created_list.getFirst());
             organisms.add(created_list.getFirst());
             created_list.removeFirst();
             bubbleOrganism();
         }
     }

     public void setOnBoard(Organism organism) {
         map[organism.getCords().x][organism.getCords().y] = organism;
     }

     public void takeFromBoard(Organism organism) {
         Point cords = organism.getCords();
         if (map[cords.x][cords.y] == organism) map[cords.x][cords.y] = null;
     }


     void destroyOrganisms() {
         while (destroy_list.size() > 0) {
             for (int i = 0; i < organisms.size(); ++i) {
                 if (organisms.get(i) == destroy_list.getLast()) {
                     organisms.remove(i);
                 }
             }
             for (Organism organism : created_list) {
                 if (organism == destroy_list.getLast()) {
                     created_list.remove(organism);
                 }
             }//czy nie można szybciej?

             destroy_list.removeLast();
         }
     }

     public boolean checkIfAlive(Organism target) {
         for (Organism organism : destroy_list) {
             if (target == organism) return false;
         }
         return true;
     }

     private Organism allocByType(Class type, Point cords)
     {
         try
         {
             if(type == world.animals.Sheep.class) {
                 return new world.animals.Sheep(cords, this);
             }

             if(type == world.plants.Grass.class) {
                 return new world.plants.Grass(cords, this);
             }
         }
         catch(Exception ex) {
            System.out.println(ex);
         }
         return null;
     }

    private void createOrganism(Class type,Point cords){
         Organism organism = allocByType(type,cords);
         map[cords.x][cords.y] = organism;
         organisms.add(organism);
         bubbleOrganism();
    }
    void addToQueue(Class type,Point point)
    {
        Organism organism = allocByType(type,point);
        setOnBoard(organism);
        created_list.addLast(organism);
    }
    private void createOrganism(Class type)
    {
        Random gen = new Random();
        Point tmp = new Point();
        do {
            tmp.x = (gen.nextInt()&Integer.MAX_VALUE)%dimensions.x;
            tmp.y = (gen.nextInt()&Integer.MAX_VALUE)%dimensions.y;
        }while(isThere(tmp)!=null);
        createOrganism(type,tmp);
    }
 }
