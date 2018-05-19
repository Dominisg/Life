package world.gui;

import world.Organism;
import world.World;
import world.WorldGrid;
import world.animals.*;
import world.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.sqrt;

public class BoardInterface extends JPanel {

    private world.Point dimensions;
    private world.Point windowsize;
    private world.World world;

    BoardInterface(World w, world.Point dim, world.Point ws) {
        world = w;
        dimensions = dim;
        windowsize = ws;
        windowsize.y -= 200;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                world.Point mouse = new world.Point(e.getX(), e.getY());
                if (world instanceof WorldGrid) {

                    world.Point cords = new world.Point(mouse.x / (windowsize.x / dimensions.x), mouse.y / (windowsize.y / dimensions.y));
                    if (world.getOrganism(cords) == null) new AnimalFactory(BoardInterface.this, cords, mouse);
                } else {
                    int dim;
                    dim = dimensions.x > dimensions.y ? dimensions.x : dimensions.y;
                    dim++;

                    int translation = (int) ((windowsize.x / dim) * 0.16);
                    world.Point result = new world.Point(0, (int) ((mouse.y - translation) / ((windowsize.x / dim) / sqrt(3) + (windowsize.x / dim) * 0.33)));

                    if (result.y % 2 == 0) result.x = mouse.x / (windowsize.x / dim);
                    else result.x = (mouse.x - windowsize.x / dim / 2) / ((windowsize.x / dim));
                    if (result.x < dimensions.x && result.y < dimensions.y && result.x >= 0 && result.y >= 0) {
                        if (world.getOrganism(result) == null) new AnimalFactory(BoardInterface.this, result, mouse);
                    }
                }
            }
        });
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(600, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ((world instanceof WorldGrid)) {
            g.setColor(new Color(112, 128, 144, 125));
            for (int i = 0; i < windowsize.x; i += 2 * windowsize.x / dimensions.x) {
                g.fillRect(i, 0, windowsize.x / dimensions.x, windowsize.y);
            }
            for (int i = 0; i < windowsize.y; i += 2 * windowsize.y / dimensions.y) {
                g.fillRect(0, i, windowsize.x, windowsize.y / dimensions.y);
            }
            g.drawLine(windowsize.x - 1, 0, windowsize.y, windowsize.x - 1);
            g.drawLine(0, windowsize.y, windowsize.x, windowsize.y);
        } else {
            int dim;
            dim = dimensions.x > dimensions.y ? dimensions.x : dimensions.y;
            dim++;
            g.setColor(new Color(112, 128, 144, 125));
            for (int j = 1; j <= dimensions.y; j++) {
                for (int i = 1; i <= dimensions.x * 2; i += 2) {
                    if (j % 2 == 1)
                        g.fillPolygon(getHexagon(i * ((windowsize.x / (dim)) / 2), ((windowsize.x / dim) + ((j - 1) * (int) (3 * (windowsize.x / dim) / 2 / sqrt(3)))), (int) ((windowsize.x / dim) / sqrt(3))));
                    else
                        g.fillPolygon(getHexagon((i * ((windowsize.x / (dim)) / 2) + (windowsize.x / (dim) / 2)), ((windowsize.x / dim) + ((j - 1) * (int) (3 * (windowsize.x / dim) / 2 / sqrt(3)))), (int) ((windowsize.x / dim) / sqrt(3))));
                }
            }
        }

        for (Organism organism : world.getOrganisms()) {
            organism.draw(g);
        }
    }

    private class AnimalFactory extends JPopupMenu implements ActionListener {
        world.Point cords;

        AnimalFactory(JPanel jpanel, world.Point point, world.Point mouse) {
            JMenuItem wolf = new JMenuItem("Wolf");
            wolf.addActionListener(this);
            JMenuItem antelope = new JMenuItem("Antelope");
            antelope.addActionListener(this);
            JMenuItem turtle = new JMenuItem("Turtle");
            turtle.addActionListener(this);
            JMenuItem fox = new JMenuItem("Fox");
            fox.addActionListener(this);
            JMenuItem sheep = new JMenuItem("Sheep");
            sheep.addActionListener(this);
            JMenuItem hogweed = new JMenuItem("Hogweed");
            hogweed.addActionListener(this);
            JMenuItem dendelion = new JMenuItem("Dendelion");
            dendelion.addActionListener(this);
            JMenuItem deadlyshade = new JMenuItem("Deadlyshade");
            deadlyshade.addActionListener(this);
            JMenuItem grass = new JMenuItem("Grass");
            grass.addActionListener(this);
            JMenuItem guarana = new JMenuItem("Guarana");
            guarana.addActionListener(this);
            cords = point;
            add(wolf);
            add(antelope);
            add(turtle);
            add(fox);
            add(sheep);
            add(hogweed);
            add(dendelion);
            add(deadlyshade);
            add(grass);
            add(guarana);
            show(jpanel, mouse.x, mouse.y);
            setVisible(true);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }


        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            switch (actionEvent.getActionCommand()) {
                case "Wolf":
                    world.createOrganism(Wolf.class, cords);
                    break;
                case "Antelope":
                    world.createOrganism(Antelope.class, cords);
                    break;
                case "Turtle":
                    world.createOrganism(Turtle.class, cords);
                    break;
                case "Fox":
                    world.createOrganism(Fox.class, cords);
                    break;
                case "Sheep":
                    world.createOrganism(Sheep.class, cords);
                    break;
                case "Hogweed":
                    world.createOrganism(Hogweed.class, cords);
                    break;
                case "Guarana":
                    world.createOrganism(Guarana.class, cords);
                    break;
                case "Grass":
                    world.createOrganism(Grass.class, cords);
                    break;
                case "Deadlyshade":
                    world.createOrganism(Deadlyshade.class, cords);
                    break;
                case "Dendelion":
                    world.createOrganism(Dendelion.class, cords);
                    break;
            }
            world.getGamescreen().repaint();
        }
    }

    Polygon getHexagon(int x, int y, int a) {
        Polygon hexagon = new Polygon();
        a -= 2;
        double b;
        for (int i = 0; i < 7; i++) {
            b = Math.PI / 3.0 * i;
            hexagon.addPoint((int) (Math.round(x + Math.sin(b) * a)), (int) (Math.round(y + Math.cos(b) * a)));
        }
        return hexagon;
    }
}
