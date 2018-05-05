package world.animals;

import sun.awt.image.ToolkitImage;
import world.World;
import world.gui.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sheep extends Animal{
    public Sheep(world.Point cords, world.World world)
    {
        super(cords,world,4,4);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/animals/graphics/sheep.png"));
            image =  tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        }
        catch(IOException ex)
        {
         System.out.println(ex);
        }
    }

}
