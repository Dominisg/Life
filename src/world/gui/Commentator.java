package world.gui;

import world.Direction;
import world.Organism;

import javax.swing.*;
import java.awt.*;

public class Commentator {
    private JTextArea log;
    private Font font = new Font("Ubuntu", Font.PLAIN, 12);

    public Commentator(JTextArea l) {
        log = l;
        log.setFont(font);
        log.setVisible(true);
    }

    public void commentFight(Organism attacker, Organism target) {
        log.append(attacker.getClass().getSimpleName() + " attacked " + target.getClass().getSimpleName() + "\n");
    }

    public void commentFightResult(Organism attacker, boolean win) {
        if (!win) log.append(attacker.getClass().getSimpleName() + " lost.\n");
        else log.append(attacker.getClass().getSimpleName() + " won.\n");
    }

    public void commentJumping(Organism organism, world.Point point) {
        log.append(organism.getClass().getSimpleName() + " jumped on field (" +
                point.x + "," + point.y + ")\n");
    }

    public void commentMovement(Organism organism, Direction dir) {
        switch (dir) {
            case DOWN:
                log.append(organism.getClass().getSimpleName() + "(" +
                        organism.getCords().x + "," + organism.getCords().y + ") moved down.\n");
                break;
            case UP:
                log.append(organism.getClass().getSimpleName() + "(" +
                        organism.getCords().x + "," + organism.getCords().y + ") moved up.\n");
                break;
            case RIGHT:
                log.append(organism.getClass().getSimpleName() + "(" +
                        organism.getCords().x + "," + organism.getCords().y + ") moved right.\n");
                break;
            case LEFT:
                log.append(organism.getClass().getSimpleName() + "(" +
                        organism.getCords().x + "," + organism.getCords().y + ") moved left.\n");
                break;
            case HEXLEFT:
                log.append(organism.getClass().getSimpleName() + "(" +
                        organism.getCords().x + "," + organism.getCords().y + ") moved hexleft(-1,1).\n");
                break;
            case HEXRIGHT:
                log.append(organism.getClass().getSimpleName() + "(" +
                        organism.getCords().x + "," + organism.getCords().y + ") moved hexright(1,-1).\n");
                break;
        }
    }

    public void commentProliferation(Organism organism, boolean success) {
        if (success) log.append(organism.getClass().getSimpleName() + "(" +
                organism.getCords().x + "," + organism.getCords().y + ")s/es reproduceed theselves with success.\n");
        else log.append(organism.getClass().getSimpleName() + "(" +
                organism.getCords().x + "," + organism.getCords().y + ") tried to reproduce themselves.\n");
    }

    public void commentEating(Organism plant, Organism animal) {
        log.append(plant.getClass().getSimpleName() + "(" +
                plant.getCords().x + "," + plant.getCords().y + ") was eaten by " + animal.getClass().getSimpleName() + "\n");
    }

    public void commentKilling(Organism plant, Organism animal) {
        log.append(plant.getClass().getSimpleName() + "(" +
                plant.getCords().x + "," + plant.getCords().y + ") killed " + animal.getClass().getSimpleName() + "\n");
    }

    public void commentSpreading(Organism organism) {
        log.append(organism.getClass().getSimpleName() + "(" +
                organism.getCords().x + "," + organism.getCords().y + ") spread.\n");
    }

    public void clear() {
        log.setText("");
    }
}
