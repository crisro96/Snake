package snake.game.java;

import java.awt.*;
import java.util.Random;

public class Food
{
    Point location = new Point();
    Main main;

    public Food(Main main)
    {
        this.main = main;
    }

    public void paint(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(location.getX() * 10,
                            location.getY() * 10,
                            10,
                            10
                            );
    }

    public void generateLocation(Snake snake, int Map[][])
    {
        Random random = new Random();
        int number = random.nextInt(810 - snake.returnSize());

        for (int height = 0; height < 27; height++)
            for (int width = 0; width < 30; width++, number--)
            {
                if (Map[height][width] == 1) { number++; }
                if(number == 0 && Map[height][width] == 0)
                {
                    location.setX(width);
                    location.setY(height);
                }
            }

    }

}
