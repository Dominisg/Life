package world.animals;

import world.Organism;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Turtle extends Animal{
    public Turtle(world.Point cords, world.World world)
    {
        super(cords,world,2,1);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/animals/graphics/turtle.png"));
            image =  tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void action() {

        if((gen.nextInt()&Integer.MAX_VALUE)%4==0)
         super.action();
    }

    @Override
    public boolean willItFend(Organism attacker) {
        return(attacker.getStrenght()<5);
    }
}