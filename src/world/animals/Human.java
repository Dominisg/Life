package world.animals;

import world.Direction;
import world.Organism;

import java.awt.event.ActionEvent;
import java.util.concurrent.Semaphore;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Human extends Animal{
    private Direction dir;
    private boolean skill_is_active = true;
    private boolean was_used = false;
    private int cooldown = -5;
    private JLabel info;



    public void setDir(Direction d)
    {
        dir=d;
    }

    public Human(world.Point cords, world.World world) {
        super(cords, world, 5, 4);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/animals/graphics/human.png"));
            image = tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        info = world.getGamescreen().getInfoLabel();

        world.getGamescreen().getBoardPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "move_up");
        world.getGamescreen().getBoardPanel().getActionMap().put("move_up", new MoveAction(Direction.UP,this));
        world.getGamescreen().getBoardPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "move_down");
        world.getGamescreen().getBoardPanel().getActionMap().put("move_down", new MoveAction(Direction.DOWN,this));
        world.getGamescreen().getBoardPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "move_right");
        world.getGamescreen().getBoardPanel().getActionMap().put("move_right", new MoveAction(Direction.RIGHT,this));
        world.getGamescreen().getBoardPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "move_left");
        world.getGamescreen().getBoardPanel().getActionMap().put("move_left", new MoveAction(Direction.LEFT,this));

    }

    public void setCooldown(int cld) {
        cooldown = cld;
        if (cld > 0) skill_is_active = true;
    }

    public int getCooldown() {
        return cooldown;
    }


    @Override
    public void action() {
        //semaphore.drainPermits();
        if (skill_is_active) info.setText("Skill is active!");
        world.Point last_cords = new world.Point(cords);
        dir = null;

        do {

            this.world.getGamescreen().repaint();
            world.takeFromBoard(this);
            try {
            } catch (Exception exc) {
                System.out.println(exc);
            }
        } while (!willBeIn(dir));

        if (dir != null) ;//commentator
        Organism collision_target = move(dir);
        if (collision_target != null) Collision(collision_target, last_cords);
        if (world.checkIfAlive(this)) world.setOnBoard(this);

//
//        if (cooldown > 2 && !was_used) {
//            if (cooldown != 6) was_used = true;
//            cooldown--;
//            // Action();
//        }
    }


    private class MoveAction extends AbstractAction {

        Direction direction;
        Human player;

        MoveAction(Direction direction, Human player) {

            this.direction = direction;
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            player.setDir(direction);
        }
    }

}


