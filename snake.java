import javax.swing.JFrame;//for the keys and such
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.LinkedList;//for linkedlist / snake body

public class Snake {

    static int size = 20;
    static String [][] maze = new String[size][size];

    static int snakeX = 4;
    static int snakeY = 3;

    
    public static void main(String[] args) {

        JFrame myJFrame = new JFrame();
        myJFrame.setAlwaysOnTop (true);

        myJFrame.setVisible(true);

        makeBoard();
        //addSnake();
        displayBoard();
        System.err.println();


        //int snakeX = 4;
        //int snakeY = 3;

        //LinkedList<String> snake = new LinkedList<String>();
        LinkedList<snakeObj> snake = new LinkedList<snakeObj>();
        maze[snakeY][snakeX] = "S";
        snakeObj body = new snakeObj(); body.addSnake(snakeY, snakeX);
        snake.addLast(body);
        snakeObj body1 = new snakeObj(); body1.addSnake(snakeY, snakeX+1);
        snake.addLast(body1);
        snakeObj body2 = new snakeObj(); body2.addSnake(snakeY, snakeX+2);
        snake.addLast(body2);
        //snake.addLast();

        displayBoard();

        myJFrame.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {        //keyPressed   //keyTyped  //keyReleased
              int keyCode = e.getKeyCode();
              if (keyCode == KeyEvent.VK_UP) {
                System.out.println("Up Arrow-Key is Released!");

                snakeObj body0 = new snakeObj();
                body0.addSnake(getSnakeY(), getSnakeX());
                snake.addFirst(body0);
                
                //if snake goes too far up, the game will end. 
                if(getSnakeY() - 1 < 0) {
                	System.out.println("You crashed into a wall, Game Over!");
                	System.exit(0);
                }else if(checkForSelfCrash(getSnakeY()-1, getSnakeX())) {
                	System.out.println("You crashed into yourself, Game Over!");
                	System.exit(0);
                }else {
                	//otherwise keep playing
	                moveSnakeByKey(getSnakeY() - 1, getSnakeX());
	                snake.removeLast();
	                AddSnakeToBoard(snake);
	                displayBoard();
                }

                
              }
              else if (keyCode == KeyEvent.VK_DOWN) {
                System.out.println("Down Arrrow-Key is Released!");
                //moveSnakeByKey(getSnakeX(),getSnakeY()+1);

                snakeObj body0 = new snakeObj();
                body0.addSnake(getSnakeY(), getSnakeX());
                snake.addFirst(body0);
                
                //if snake goes too far down, the game will end.
                if(getSnakeY() + 1 > size-1) {
                	System.out.println("You crashed into a wall, Game Over!");
                	System.exit(0);
                }else if(checkForSelfCrash(getSnakeY()+1, getSnakeX())) {
                	System.out.println("You crashed into yourself, Game Over!");
                	System.exit(0);
                }else {
	                moveSnakeByKey(getSnakeY() + 1, getSnakeX());
	                snake.removeLast();
	                AddSnakeToBoard(snake);
	                displayBoard();
                }
              }
              else if (keyCode == KeyEvent.VK_LEFT) {
                System.out.println("Left Arrrow-Key is Released!");
                //moveSnakeByKey(getSnakeX()-1,getSnakeY());

                snakeObj body0 = new snakeObj();
                body0.addSnake(getSnakeY(), getSnakeX());
                snake.addFirst(body0);
                
                //if snake goes too far left, game over!
                if(getSnakeX() - 1 < 0) {
                	System.out.println("You crashed into a wall, Game Over!");
                	System.exit(0);
                }else if(checkForSelfCrash(getSnakeY(), getSnakeX()-1)) {
                	System.out.println("You crashed into yourself, Game Over!");
                	System.exit(0);
                }else {
	                moveSnakeByKey(getSnakeY(), getSnakeX() - 1);
	                snake.removeLast();
	                AddSnakeToBoard(snake);
	                displayBoard();
                }
              }
              else if (keyCode == KeyEvent.VK_RIGHT) {
               System.out.println("Right Arrrow-Key is Released!");
               //moveSnakeByKey(getSnakeX(),getSnakeY());


               snakeObj body0 = new snakeObj();
               body0.addSnake(getSnakeY(), getSnakeX());
               snake.addFirst(body0);
               
               //if snake goes too far right, game over!
               if(getSnakeX() + 1 > size-1) {
            	   System.out.println("You crashed into a wall, Game Over!");
            	   System.exit(0);
               }else if(checkForSelfCrash(getSnakeY(), getSnakeX()+1)) {
               	   System.out.println("You crashed into yourself, Game Over!");
               	   System.exit(0);
               }else {
	               moveSnakeByKey(getSnakeY(), getSnakeX() + 1);
	               snake.removeLast();
	               AddSnakeToBoard(snake);
	               //snake.clear();
	               displayBoard();
               }
              }
            }
          });
        

    }
    
    public static boolean checkForSelfCrash(int snake_y, int snake_x) {
    	return maze[snake_y][snake_x] == ".";
    }
    
    public static void moveSnakeByKey(int snake_y, int snake_x){
        maze[snakeY][snakeX] = ".";
        snakeX = snake_x;
        snakeY = snake_y;
        maze[snakeY][snakeX] = "S";
    }

    public static int getSnakeX(){
        return snakeX;
    }

    public static int getSnakeY(){
        return snakeY;
    }
    
    public static void makeBoard() {
        for (int i =0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                maze[i][j] = "0";
            }
        }
    }
    public static void addSnake() { 
        int snakeSize = 3;
        int snakeRow = size/2;
        int snakeCol = size/2;
        for (int i = 0; i < snakeSize; i++) {
            maze[snakeRow][snakeCol + i] = "S";
        }
    }

    public static void AddSnakeToBoard(LinkedList<snakeObj> snake){
        for(int i =0; i<snake.size(); i++){
            maze[snake.get(i).y][snake.get(i).x] = snake.get(i).type;
        }
    }

    public static void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}






class snakeObj {     //stated coding at 1pm
    int x;
    int y;
    String type = ".";
    void addSnake(int Y, int X){
        x=X;
        y=Y;
    }
}

