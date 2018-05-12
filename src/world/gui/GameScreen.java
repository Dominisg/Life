package world.gui;
import javax.swing.*;
import world.Point;
import world.Game;
@SuppressWarnings("null")
public class GameScreen extends JFrame {


 private JLabel info;
 private BoardInterface bi;
 private JTextArea log;

 public JLabel getInfoLabel() {
  return info;
 }

 public JPanel getBoardPanel() { return bi; }

 public JTextArea getLog(){ return log; }

 public GameScreen(Game game, Point size) {

  new WorldCreateDialog(this, game);
  if (game.getWorld() == null) System.exit(0);
  setSize(size.x + 40, size.y + 40);

  setTitle("Life");
  setLayout(new java.awt.FlowLayout());

  JButton skill = new JButton("Skill");
  info = new JLabel("You are able to use skill");
  add(info);
  add(skill);
  info.setVisible(false);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  bi = new BoardInterface(game.getWorld(), game.getWorld().getDimensions(), size);
  add(bi);


  JButton nt = new JButton("Next turn");
  JButton save = new JButton("Save");
  JButton load = new JButton("Load");
  nt.addActionListener(game);
  save.addActionListener(game);
  load.addActionListener(game);
  add(nt);
  add(save);
  add(load);

  log = new JTextArea(5,60);
  log.setEditable(false);
  JScrollPane scrollPane = new JScrollPane(log);
  add(scrollPane);
  scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  //add(log);
  setVisible(true);


 }

}

