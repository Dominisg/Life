package world;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Direction {
    RIGHT(1,0),LEFT(-1,0),UP(0,-1,-1,-1),DOWN(1,1,0,1),HEXRIGHT(1,-1,0,-1),HEXLEFT(0,1,-1,1);

    private Point translation_even = new Point();
    private Point translation_odd = new Point();

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random gen = new Random();


    Direction(int x, int y)
    {
        translation_odd.x = x;
        translation_odd.y = y;
        translation_even.x = x;
        translation_even.y = y;
    }
    Direction(int xo, int yo, int xe, int ye)
    {
        translation_odd.x = xo;
        translation_odd.y = yo;
        translation_even.x = xe;
        translation_even.y = ye;
    }

    public void translate (Point point)
    {
        if(this==Direction.DOWN)
        {
            point.x += translation_even.x;
            point.y += translation_even.y;

        }
        else {
            point.x += translation_odd.x;
            point.y += translation_odd.y;
        }
    }

    public void translateHex (Point point)
    {
        if(point.y%2==1) {
            point.x += translation_odd.x;
            point.y += translation_odd.y;
        }
        else
        {
            point.x += translation_even.x;
            point.y +=translation_even.y;
        }
    }



    public static Direction getDir(int i)
    {

        return VALUES.get(i);
    }

    public static Direction randomDirection()
    {
        return VALUES.get(gen.nextInt(4));
    }
    public static Direction randomDirectionHex()
    {
        return VALUES.get(gen.nextInt(VALUES.size()));
    }
}

