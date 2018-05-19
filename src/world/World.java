package world;
import com.sun.org.apache.xpath.internal.operations.String;
import world.animals.Human;
import world.gui.Commentator;
import world.gui.GameScreen;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;

public abstract class World {


    final public Point screensize = new Point(600, 800);
    protected Point dimensions;
    private Commentator commentator;
    private Organism[][] map;
    private GameScreen gamescreen;
    private Vector<Organism> organisms = new Vector<>();
    private Vector<Organism> created_list = new Vector<Organism>();
    private Vector<Organism> destroy_list = new Vector<>();

    public abstract boolean createInNeighbour(Point point, Class type);
    abstract public Point getFieldsize();

    World(Point dim) {
        map = new Organism[dim.x][dim.y];
        dimensions = dim;
    }

    public Vector<Organism> getOrganisms() {
        return organisms;
    }

    public Vector<Organism> getRecentlyCreatedOrganisms() {
        return created_list;
    }

    public Commentator getCommentator() {
        return commentator;
    }
    void setGamescreen(GameScreen gs) {
        gamescreen = gs;
    }

    void setCommentator(Commentator c) {
        commentator = c;
    }

    public GameScreen getGamescreen() {
        return gamescreen;
    }

    public Point getDimensions() {
        return dimensions;
    }



    void createStartingBoard() {
        createOrganism(world.animals.Wolf.class);
        //createOrganism(world.animals.Human.class);
        createOrganism(world.animals.Sheep.class);
        createOrganism(world.animals.Sheep.class);
        createOrganism(world.animals.Turtle.class);
        createOrganism(world.animals.Antelope.class);
        createOrganism(world.animals.Fox.class);
        createOrganism(world.plants.Guarana.class);
        createOrganism(world.plants.Hogweed.class);
        createOrganism(world.plants.Grass.class);
        createOrganism(world.plants.Deadlyshade.class);
        createOrganism(world.plants.Dendelion.class);
    }

    void performRound() {
        for (Organism organism : organisms) {
            if (checkIfAlive(organism)) organism.action();
        }
        destroyOrganisms();
        addCreatedOrganism();
        gamescreen.repaint();
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
            setOnBoard(created_list.firstElement());
            organisms.add(created_list.firstElement());
            created_list.removeElementAt(0);
            bubbleOrganism();
        }
    }


    public void setOnBoard(Organism organism) {
        map[organism.getCords().x][organism.getCords().y] = organism;
    }

    public void takeFromBoard(Organism organism) {
        Point cords = organism.getCords();
        if (map[cords.x][cords.y].equals(organism)) map[cords.x][cords.y] = null;
    }

    public Organism getOrganism(Point cords) {
        return map[cords.x][cords.y];
    }


    void destroyOrganisms() {
        boolean found = false;
        while (destroy_list.size() > 0) {
            for (int i = 0; i < organisms.size(); ++i) {
                if (organisms.get(i).equals(destroy_list.lastElement())) {
                    organisms.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                for (Organism organism : created_list) {
                    if (organism.equals(destroy_list.lastElement())) {
                        created_list.remove(organism);
                        break;
                    }
                }//czy nie można szybciej?
            }
            found = false;
            destroy_list.removeElementAt(destroy_list.size() - 1);
        }
    }

    public boolean checkIfAlive(Organism target) {

        for (Organism organism : destroy_list) {
            if (target.equals(organism)) return false;
        }
        return true;
    }

    private Organism allocByType(Class type, Point cords) {
        try {
            if (type == world.animals.Sheep.class) {
                return new world.animals.Sheep(cords, this);
            }
            if (type == world.animals.Human.class) {
                return new world.animals.Human(cords, this);
            }
            if (type == world.animals.Wolf.class) {
                return new world.animals.Wolf(cords, this);
            }
            if (type == world.animals.Turtle.class) {
                return new world.animals.Turtle(cords, this);
            }
            if (type == world.animals.Antelope.class) {
                return new world.animals.Antelope(cords, this);
            }
            if (type == world.animals.Fox.class) {
                return new world.animals.Fox(cords, this);
            }
            if (type == world.plants.Grass.class) {
                return new world.plants.Grass(cords, this);
            }
            if (type == world.plants.Dendelion.class) {
                return new world.plants.Dendelion(cords, this);
            }
            if (type == world.plants.Guarana.class) {
                return new world.plants.Guarana(cords, this);
            }
            if (type == world.plants.Deadlyshade.class) {
                return new world.plants.Deadlyshade(cords, this);
            }
            if (type == world.plants.Hogweed.class) {
                return new world.plants.Hogweed(cords, this);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Organism createOrganism(Class type, Point cords) {
        Organism organism = allocByType(type, cords);
        map[cords.x][cords.y] = organism;
        organisms.add(organism);
        bubbleOrganism();
        return organism;
    }

    void addToQueue(Class type, Point point) {
        Organism organism = allocByType(type, point);
        setOnBoard(organism);
        created_list.add(organism);
    }

    private void createOrganism(Class type) {
        Random gen = new Random();
        Point tmp = new Point();
        do {
            tmp.x = (gen.nextInt() & Integer.MAX_VALUE) % dimensions.x;
            tmp.y = (gen.nextInt() & Integer.MAX_VALUE) % dimensions.y;
        } while (isThere(tmp) != null);
        createOrganism(type, tmp);
    }

    public void removeOrganism(Organism target) {
        Point tmp = target.getCords();
        if (map[tmp.x][tmp.y] == target) map[tmp.x][tmp.y] = null;
        for (int i = 0; i < organisms.size(); ++i) {
            if (organisms.get(i) == target) {
                destroy_list.add(target);
                return;
            }
        }
        for (Organism organism : created_list) {
            if (organism == target) {
                destroy_list.add(target);
                return;
            }
        }
    }

    void save() {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt", "text");
        jf.setFileFilter(filter);
        jf.showDialog(gamescreen, "Save game");
        try {
            File file = jf.getSelectedFile();
            PrintWriter out = new PrintWriter(file);
            out.println(dimensions);
            for (int i = 0; i < organisms.size(); i++) {
                out.print(organisms.get(i).getClass());
                out.print("\n");
                out.print(organisms.get(i).getCords());
                out.print(" ");
                out.print(organisms.get(i).getStrenght());
                out.print(" ");
                if (organisms.get(i) instanceof Human) out.print(((Human) organisms.get(i)).getCooldown());
                out.print("\n");
            }
            out.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    void load() {
        commentator.clear();
        map = null;
        organisms.clear();
        created_list.clear();
        destroy_list.clear();
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt", "text");
        jf.setFileFilter(filter);
        jf.showDialog(gamescreen, "Load game");
        try {
            File file = jf.getSelectedFile();
            Scanner in = new Scanner(file);

            dimensions.x = in.nextInt();
            dimensions.y = in.nextInt();
            map = new Organism[dimensions.x][dimensions.y];
            while (true) {
                Class type;
                Point cords = new Point();
                int strenght;
                if (in.next() == null) break;
                type = Class.forName(in.next());
                cords.x = in.nextInt();
                cords.y = in.nextInt();
                strenght = in.nextInt();
                Organism organism = createOrganism(type, cords);
                organism.setStrenght(strenght);
                if (organism.getClass() == Human.class) ((Human) organism).setCooldown(in.nextInt());
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        gamescreen.repaint();
    }
}


