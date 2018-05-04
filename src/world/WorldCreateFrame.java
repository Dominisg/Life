package world;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WorldCreateFrame extends JFrame implements ActionListener {

   private JRadioButton hex;
   private JRadioButton square;
   private JTextField height;
   private JTextField width;


    WorldCreateFrame() {
        super("Create your world");
        setSize(400, 120);
        setLayout(new java.awt.FlowLayout());
        JLabel question = new JLabel("Board should have : ");
        hex = new JRadioButton("Hexagonal Grid");
        square = new JRadioButton("Square Grid");
        JLabel heightlabel = new JLabel("height");
        height = new JTextField(5);
        JLabel widthlabel = new JLabel(" width");
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

World Create_world(Point dim,boolean hex)
{
if(hex)return new WorldHex(dim);
else return new WorldGrid(dim);
}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("Ok"))
        {
            Point dim=new Point();
            try {
                dim.x = Integer.parseInt(width.getText());
                dim.y = Integer.parseInt(height.getText());
            }
            catch(NumberFormatException e)
            {
                JLabel warning = new JLabel("I need correct values");
                add(warning);
                setVisible(true);
            }
            if(dim.x>0 && dim.y>0 && (hex.isSelected() || square.isSelected()))
            {
                if(hex.isSelected())Create_world(dim,true);
                else Create_world(dim,false);
                 setVisible(false);

            }
        }


    }

}
