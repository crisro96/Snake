package snake.game.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
public class Main extends JPanel
{
    public static int WIDTH = 305;
    public static int HEIGHT = 300;
    public static int W_BLOCKS = 30;
    public static int H_BLOCKS = 28;

    private int[][] Map = new int[28][30];

    boolean foodGenerated;

    Snake S = new Snake(this);
    Food F = new Food(this);

    public void initializeMap()
    {
        for (int i = 0; i < H_BLOCKS; i++)
            for (int j = 0; j < W_BLOCKS; j++)
                Map[i][j] = 0;
    }

    public void drawMap(Snake S)
    {
        for(int index = 0; index < S.returnSize(); index++)
            Map[S.returnY(index)][S.returnX(index)] = 1;

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        S.paint(g2d);
        if(!foodGenerated)
        {
            F.generateLocation(S, Map);
            foodGenerated = true;

        }
        F.paint(g2d);
    }

    public Main()
    {
        addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                //do nothing
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                S.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                S.keyReleased();
            }
        });
        setFocusable(true);
    }

    public void gameOver(String causeOfDeath)
    {
        JOptionPane.showMessageDialog(this, "Game Over", causeOfDeath, JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Snake in Java BETA");

        Main snake = new Main();
        frame.add(snake);

        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        snake.S.addBlock(15,14);

        snake.initializeMap();
        snake.F.generateLocation(snake.S, snake.Map);
        snake.foodGenerated = true;

        while(true)
        {
            snake.S.updateMain(snake);
            snake.S.move();
            snake.drawMap(snake.S);
            snake.repaint();

            try
            {
                TimeUnit.MILLISECONDS.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


}
