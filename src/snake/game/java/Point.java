package snake.game.java;

public class Point
{
    private int X;
    private int Y;

    public int getX()
    {
        return X;
    }

    public int getY()
    {
        return Y;
    }

    public void setX(int x)
    {
        X = x;
    }

    public void setY(int y)
    {
        Y = y;
    }

    public int pointDirection(Point p)
    {
        //Sunt pe aceeasi linie
        if (p.getX() == X)
            //Punctul p e in stanga
            if (p.getY() == Y - 1)
                return 2;
            //Punctul p e in dreapta
             else
                return 4;
        //Sunt pe aceeasi coloana
        else
            //Punctul p e sus
            if (p.getX() == X-1)
                return 1;
            //Punctul p e jos
            else
                return 3;

    }

    public boolean isSame(Point p)
    {
        if (X == p.getX() && Y == p.getY()) return true;

        return false;
    }
}
