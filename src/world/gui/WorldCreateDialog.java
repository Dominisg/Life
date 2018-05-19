package world.gui;

import world.Game;
import world.Point;
import world.WorldGrid;
import world.WorldHex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WorldCreateDialog extends JDialog implements ActionListener {

    Game game;
    private Point dim;
    private JRadioButton hex;
    private JRadioButton square;
    private JTextField height;
    private JTextField width;


    WorldCreateDialog(Window owner, Game g) {
        super(owner, "Create your world", ModalityType.DOCUMENT_MODAL);
        game = g;
        setSize(400, 120);
        setLayout(new java.awt.FlowLayout());
        JLabel question = new JLabel("Board should have : ");
        JLabel heightlabel = new JLabel("height");
        JLabel widthlabel = new JLabel(" width");
        hex = new JRadioButton("Hexagonal Grid");
        square = new JRadioButton("Square Grid");
        height = new JTextField(5);
        width = new JTextField(5);
        JButton ok = new JButton("Ok");
        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(hex);
        buttonGroup.add(square);

        ok.addActionListener(this);

        add(question);
        add(hex);
        add(square);
        add(heightlabel);
        add(height);
        add(widthlabel);
        add(width);
        add(ok);

        setVisible(true);
    }

    private void createWorld(Point dim, boolean hex) {
        if (hex) game.setWorld(new WorldHex(dim));
        else game.setWorld(new WorldGrid(dim));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Ok")) {
            dim = new Point();
            try {
                dim.x = Integer.parseInt(width.getText());
                dim.y = Integer.parseInt(height.getText());
            } catch (NumberFormatException e) {
                JLabel warning = new JLabel("I need correct values");
                add(warning);
                setVisible(true);
            }
            if (dim.x > 0 && dim.y > 0 && (hex.isSelected() || square.isSelected())) {
                if (hex.isSelected()) createWorld(dim, true);
                else createWorld(dim, false);
                dispose();

            }
        }


    }

}
