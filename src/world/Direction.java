package world;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Direction {
    RIGHT(1,0),LEFT(-1,0),UP(0,-1),DOWN(0,1),HEXRIGHT(1,1),HEXLEFT(-1,-1);

    private Point translation = new Point();

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random gen = new Random();


    Direction(int x, int y)
    {
        translation.x = x;
        translation.y = y;
    }

    public void translate (Point point)
    {
            point.x +=translation.x;
            point.y +=translation.y;
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

