package world.plants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grass extends Plant {
    public Grass(world.Point cords, world.World world) {

        super(cords, world, 0, 0);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/plants/graphics/grass.png"));
            image =  tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
}




