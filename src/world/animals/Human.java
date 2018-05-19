package world.animals;
import world.Direction;
import world.Organism;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Human extends Animal{
    private Direction dir;
    private boolean skill_is_active = true;
    private int cooldown = -5;
    private JLabel info;
    private JLabel dirlabel;


    public void setDir(Direction d)
    {
        dir=d;
        dirlabel.setText("  "+d.toString() + "  ");
    }

    public Human(world.Point cords, world.World world) {
        super(cords, world, 5, 4);
        try {
            BufferedImage tmp = ImageIO.read(new File("src/world/animals/graphics/human.png"));
            image = tmp.getScaledInstance(world.getFieldsize().x, world.getFieldsize().y, Image.SCALE_FAST);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        skill_is_active=false;
        info = world.getGamescreen().getInfoLabel();
        dirlabel = world.getGamescreen().getDirLabel();
        dir=Direction.randomDirection();
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
        if (!skill_is_active && cooldown>=-5) info.setText("You can't use your skill!");
        world.Point last_cords = new world.Point(cords);
        world.takeFromBoard(this);
         if(willBeIn(dir)) {
             Organism collision_target = move(dir);

             if(skill_is_active && willBeIn(dir))
             {
                 if(cooldown>=2 || gen.nextBoolean())
                 collision_target=null;
                 collision_target = move(dir);
             }
             if (collision_target != null) Collision(collision_target, last_cords);
         }
        if (world.checkIfAlive(this)) world.setOnBoard(this);
        cooldown--;
        if(cooldown<=0)skill_is_active=false;
        if(cooldown<=-5)info.setText("You are able to use skill");
    }

   public void activateSkill()
    {
        info.setText("Skill is active");
        if (cooldown <= -5) {
            skill_is_active = true;
            cooldown = 5;
        }
    }
}


