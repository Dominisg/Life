package world.plants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dendelion extends Plant {
    public Dendelion(world.Point cords, world.World world) {

        super(cords, world, 0, 0);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/plants/graphics/dendelion.png"));
            image =  tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
    @Override
    public void action()
    {
        for(int i=0;i<3;i++)
        {
            if(spread())return;
        }
    }
}




