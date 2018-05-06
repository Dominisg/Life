package world.gui;

import javax.swing.*;
import world.Point;
import world.Game;



public class GameScreen extends JFrame {

   public GameScreen(Game game,Point size) {

       new WorldCreateDialog(this,game);
        if(game.getWorld()==null)System.exit(0);
        setSize(size.x +40,size.y+40);

        setTitle("Life");
        setLayout(new java.awt.FlowLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoardInterface bi = new BoardInterface(game.getWorld(),game.getWorld().getDimensions(),size);
        add(bi);

        JButton nt = new JButton("Next turn");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        nt.addActionListener(game);
        add(nt);
        setVisible(true);
    }

}

