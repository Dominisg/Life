package world;

import world.gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    private World world;

    public void setWorld(World w)
    {
        world=w;
    }
    public World getWorld(){return world;}

   public void startGame()
    {

    }

    public Game() {

        GameScreen gs =new GameScreen(this,new Point(600,800));
        world.setGamescreen(gs);
        world.createStartingBoard();
        gs.repaint();
    }

    
    public void actionPerformed(ActionEvent ae)
    {
        
    }

}
