package world.plants;

import world.Organism;
import world.Point;
import world.WorldGrid;
import world.WorldHex;
import world.animals.Animal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hogweed extends Plant{
    public Hogweed (world.Point cords, world.World world) {

        super(cords, world, 10, 0);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/plants/graphics/hogweed.png"));
            image = tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void beEaten(Organism organism)
    {
        world.removeOrganism(organism);
        world.removeOrganism(this);
    }

    @Override
    public void action() {
        Point tmp = new Point(cords);
            tmp.x++;
            kill(tmp);
            tmp.x -= 2;
            kill(tmp);
        if(world instanceof WorldGrid) {
            tmp.x++;
            tmp.y++;
            kill(tmp);
            tmp.y -= 2;
            kill(tmp);
        }
        else
            {
                if(tmp.y%2==1) {
                    tmp.x++;
                    tmp.y--;
                    kill(tmp);
                }
                else {
                    tmp.y--;
                    kill(tmp);
                }
                tmp.y += 2;
                tmp.x++;
                kill(tmp);
                tmp.y -= 2;
                kill(tmp);
                tmp.x--;
                tmp.y+=2;
                kill(tmp);
        }
        super.action();
    }


   private  void kill(Point tmp)
    {
        Organism ref;
        if((ref = world.isThere(tmp))!=null){
            if(ref instanceof Animal) {
                world.removeOrganism(ref);
                world.getCommentator().commentKilling(this,ref);
            }
        }

    }
}

