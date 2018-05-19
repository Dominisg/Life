package world.animals;
import world.*;
import world.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fox extends Animal {
    public Fox(world.Point cords, world.World world) {
        super(cords, world, 3, 7);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/animals/graphics/fox.png"));
            image = tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void action() {
        world.takeFromBoard(this);
        Point last_cords =new Point(cords);
        Direction dir = whereFoxGoes();
        if(dir==null) {
            world.setOnBoard(this);
            return;
        }world.getCommentator().commentMovement(this,dir);
        Organism collision_target = move(dir);
        if(collision_target!=null)Collision(collision_target,last_cords);
        if(world.checkIfAlive(this))world.setOnBoard(this);
    }

    private Direction whereFoxGoes() {
        boolean sowhere[] = new boolean[6];

        sowhere[Direction.RIGHT.ordinal()] = canGoDir(Direction.RIGHT);
        sowhere[Direction.LEFT.ordinal()] = canGoDir(Direction.LEFT);
        sowhere[Direction.UP.ordinal()] = canGoDir(Direction.UP);
        sowhere[Direction.DOWN.ordinal()] = canGoDir(Direction.DOWN);
        if(world instanceof WorldHex) {
            sowhere[Direction.HEXRIGHT.ordinal()] = canGoDir(Direction.HEXRIGHT);
            sowhere[Direction.HEXLEFT.ordinal()] = canGoDir(Direction.HEXLEFT);
        }

        int suma = 0;
        for (boolean where : sowhere) {
            if (where) suma++;
        }

        if (suma == 0) return null;

        while (true) {
            if(world instanceof WorldGrid)
                suma = gen.nextInt(4);
            else
                suma = gen.nextInt(6);
            if (sowhere[suma]) return Direction.getDir(suma);
        }
    }

    private boolean canGoDir(Direction dir) {
        world.Point tmp = new world.Point(cords);
        dir.translate(tmp);
        if (world.isThere(tmp) == null || world.isThere(tmp).getStrenght() <= strenght) {
            return tmp.x < world.getDimensions().x && tmp.x >= 0 && tmp.y < world.getDimensions().y && tmp.y >= 0;
        }
        return false;
    }
}

