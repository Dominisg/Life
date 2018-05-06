package world.gui;

import world.Organism;
import world.World;
import world.WorldGrid;
import world.WorldHex;

import javax.swing.*;
import java.awt.*;

public class BoardInterface extends JPanel {

    private world.Point dimensions;
    private world.Point windowsize;
    private world.World world;


     BoardInterface(World w, world.Point dim, world.Point ws)
    {
        world=w;
        dimensions = dim;
        windowsize = ws;
        windowsize.y-=200;
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(600, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if((world instanceof WorldGrid)) {
            g.setColor(new Color(112, 128, 144, 125));
            for (int i = 0; i < windowsize.x; i += 2 * windowsize.x / dimensions.x) {
                g.fillRect(i, 0, windowsize.x / dimensions.x, windowsize.y);
            }
            for (int i = 0; i < windowsize.y; i += 2 * windowsize.y / dimensions.y) {
                g.fillRect(0, i, windowsize.x, windowsize.y / dimensions.y);
            }
            g.drawLine(windowsize.x - 1, 0, windowsize.y, windowsize.x - 1);
            g.drawLine(0, windowsize.y, windowsize.x, windowsize.y);
        }

        for(Organism organism : world.getOrganisms())
        {
            organism.draw(g);
        }




    }

}
