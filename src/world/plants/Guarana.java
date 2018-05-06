package world.plants;

import world.Organism;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Guarana extends Plant {
    public Guarana(world.Point cords, world.World world) {

        super(cords, world, 0, 0);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/plants/graphics/guarana.png"));
            image = tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void beEaten(Organism organism)
    {
        organism.setStrenght(organism.getStrenght()+3);
        world.removeOrganism(this);
    }
}