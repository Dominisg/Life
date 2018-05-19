package world.gui;

import javax.swing.*;

import world.Point;
import world.Game;

public class GameScreen extends JFrame {
    private JLabel info;
    private JLabel dir;
    private BoardInterface bi;
    private JTextArea log;

    public JLabel getInfoLabel() {
        return info;
    }

    public JLabel getDirLabel() {
        return dir;
    }

    public JPanel getBoardPanel() {
        return bi;
    }

    public JTextArea getLog() {
        return log;
    }

    public GameScreen(Game game, Point size) {

        if (game.getWorld() == null)
            new WorldCreateDialog(this, game);
        if (game.getWorld() == null) System.exit(0);
        setSize(size.x + 40, size.y + 40);

        setTitle("Life");
        setLayout(new java.awt.FlowLayout());
        JButton skill = new JButton("Skill");
        dir = new JLabel("");
        info = new JLabel("You are able to use skill");
        add(info);
        add(skill);
        info.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bi = new BoardInterface(game.getWorld(), game.getWorld().getDimensions(), size);
        add(bi);
        add(dir);
        dir.setVisible(true);
        JButton nt = new JButton("Next turn");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        nt.addActionListener(game);
        save.addActionListener(game);
        load.addActionListener(game);
        skill.addActionListener(game);

        //Bo po przycisnieciu guzikow przestawal dzialać keylistener
        addKeyListener(game.getWorld());
        nt.addKeyListener(game.getWorld());
        save.addKeyListener(game.getWorld());
        load.addKeyListener(game.getWorld());
        skill.addKeyListener(game.getWorld());

        add(nt);
        add(save);
        add(load);

        log = new JTextArea(5, 60);
        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);
    }

}


