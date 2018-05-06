package world.animals;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wolf extends Animal{
    public Wolf (world.Point cords, world.World world)
    {
        super(cords,world,9,5);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/animals/graphics/wolf.png"));
            image =  tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }

}
