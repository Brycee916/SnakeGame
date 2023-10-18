import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel implements ActionListener{
    
    private class Tile{
        int x;
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    //snake
    Tile snakeHead;

    //food
    Tile food;
    Random random;

    //game logic
    Timer gameLoop;

    SnakeGame(int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);

        snakeHead = new Tile(5,5);

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //draw gridlines
        for(int i = 0; i < boardWidth/tileSize; i++){
            //x1, y1, x2, y2
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); //vertical lines
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize); //horizontal lines
        }

        //food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

    }

    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize); //x=0-24
        food.y = random.nextInt(boardHeight/tileSize); //y=0-24
    }

    @Override//is called every 100ms
    public void actionPerformed(ActionEvent e) {
        repaint(); //calls draw
    }

}
