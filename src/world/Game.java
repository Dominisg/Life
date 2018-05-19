package world;
import world.animals.Human;
import world.gui.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Game implements ActionListener {
    private World world;
    GameScreen gs;
    public void setWorld(World w)
    {
        world=w;
    }
    public World getWorld(){return world;}


    public Game() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                gs = new GameScreen(Game.this, new Point(600, 800));
                world.setGamescreen(gs);
                world.setCommentator(new Commentator(gs.getLog()));
                world.createStartingBoard();
                gs.repaint();
            }
        });
    }


    public void actionPerformed(ActionEvent ae)
    {
        switch(ae.getActionCommand()) {
            case "Next turn":
                world.performRound();
                break;
            case "Save":
                world.save();
                break;
            case "Load":
                world.load();
                break;
        }
    }

}
