package world.gui;

import javax.swing.*;
import world.Point;
import world.Game;


public class GameScreen extends JFrame {

   public GameScreen(Game game,Point size) {

       new WorldCreateDialog(this,game);
        if(game.getWorld()==null)System.exit(0);
        setSize(size.x,size.y);
        setTitle("Life");
        setLayout(new java.awt.GridLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoardInterface bi = new BoardInterface(game.getWorld(),game.getWorld().getDimensions(),size);
        add(bi);

        setVisible(true);
    }

}

