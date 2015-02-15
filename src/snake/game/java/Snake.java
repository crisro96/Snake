package snake.game.java;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake
{
    private int keyPress = 0;
    private int direction = 0;

    private List<Point> blockCoordinate = new ArrayList<Point>();

    private Main main;

    public Snake(Main main)
    {
        this.main = main;
    }

    public void keyPressed(KeyEvent e)
    {
        if(keyPress == 0)
        {
            keyPress = 1;

            int d = keyDirection(e);
            if(direction == 0)
            {
                direction = d;
                return;
            }
            if( d%2 != direction%2 && d != 0)
                direction = d;
        }
    }

    public void keyReleased()
    {
        keyPress = 0;
    }

    public Point moveRight(int index)
    {
        Point location = new Point();

        location.setX(blockCoordinate.get(index).getX());
        location.setY(blockCoordinate.get(index).getY());

        if (blockCoordinate.get(index).getX() != 29)
            location.setX(location.getX() + 1);

        else
            main.gameOver("You hit a wall!");

        return location;
    }

    public Point moveLeft(int index)
    {
        Point location = new Point();

        location.setX(blockCoordinate.get(index).getX());
        location.setY(blockCoordinate.get(index).getY());

        if (blockCoordinate.get(index).getX() != 0)
            location.setX(location.getX() - 1);

        else
            main.gameOver("You hit a wall!");

        return location;

    }

    public Point moveUp(int index)
    {
        Point location = new Point();

        location.setX(blockCoordinate.get(index).getX());
        location.setY(blockCoordinate.get(index).getY());

        if (blockCoordinate.get(index).getY() != 0)
            location.setY(location.getY() - 1);

        else
            main.gameOver("You hit a wall!");

        return location;

    }

    public Point moveDown(int index)
    {
        Point location = new Point();

        location.setX(blockCoordinate.get(index).getX());
        location.setY(blockCoordinate.get(index).getY());

        if (blockCoordinate.get(index).getY() != 27)
            location.setY(location.getY() + 1);

        else
            main.gameOver("You hit a wall!");

        return location;

    }

    public int returnSize()
    {
        return blockCoordinate.size();
    }

    public int returnY(int index)
    {
        return blockCoordinate.get(index).getY();
    }

    public int returnX(int index)
    {
        return blockCoordinate.get(index).getX();
    }

    public void addBlock(int y, int x)
    {
        Point p = new Point();

        p.setX(x);
        p.setY(y);

        blockCoordinate.add(p);
    }

    public void paint(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.BLACK);
        for (int i = 0; i < blockCoordinate.size(); i++)
            graphics2D.fillRect(blockCoordinate.get(i).getX() * 10, blockCoordinate.get(i).getY() * 10, 10, 10);

    }

    public void updateMain(Main main)
    {
        this.main = main;

    }

    public void move()
    {
        Point location;

        switch (direction)
        {
            case 1:
                location = moveLeft(blockCoordinate.size() - 1);

                break;

            case 2:
                location = moveUp(blockCoordinate.size() - 1);

                break;

            case 3:
                location = moveRight(blockCoordinate.size() - 1);

                break;

            case 4:
                location = moveDown(blockCoordinate.size() - 1);

                break;
            default:
                return;
        }

        if(checkCollision(location))
        {
            main.gameOver("You ate yourself!");
        }

        if(main.F.location.isSame(location))
        {
            addBlock(location.getY(), location.getX());
            main.foodGenerated = false;

            return;
        }

        //Move the snake
        for (int index = 0; index < blockCoordinate.size() - 1; index++)
        {
            blockCoordinate.get(index).setX(blockCoordinate.get(index + 1).getX());
            blockCoordinate.get(index).setY(blockCoordinate.get(index + 1).getY());
        }

        blockCoordinate.get(blockCoordinate.size() - 1).setX(location.getX());
        blockCoordinate.get(blockCoordinate.size() - 1).setY(location.getY());
    }

    private boolean checkCollision(Point location)
    {
        for (int index = 0; index < blockCoordinate.size() - 1; index++)
            if (blockCoordinate.get(index).isSame(location))
            {
                return true;
            }
        return false;
    }

    public int keyDirection(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                return 1;

            case KeyEvent.VK_UP:
                return 2;

            case KeyEvent.VK_RIGHT:
                return 3;

            case KeyEvent.VK_DOWN:
                return 4;
        }

        return 0;
        //return move();
    }
}
