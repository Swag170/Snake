

import javax.swing.JFrame;//for the keys and such
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.LinkedList;//for linkedlist / snake body
import java.util.Random;

public class Snake {

    static int size = 20;
    static String [][] maze = new String[size][size];
    static int snakeX = 4;
    static int snakeY = 3;
    static int foodRows;
    static int foodCols;
    static int foodCounter = 0;
    public static void main(String[] args) {

        JFrame myJFrame = new JFrame();
        myJFrame.setAlwaysOnTop (true);

        myJFrame.setVisible(true);
     
        makeBoard();
        spawnFood();
        displayBoard();
        System.err.println();

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
                dealWithKeyPress(getSnakeY()-1, getSnakeX(), snake);
              }
              else if (keyCode == KeyEvent.VK_DOWN) {
                 System.out.println("Down Arrow-Key is Released!");
                 dealWithKeyPress(getSnakeY()+1, getSnakeX(), snake);
              }
              else if (keyCode == KeyEvent.VK_LEFT) {
                 System.out.println("Left Arrow-Key is Released!");
                 dealWithKeyPress(getSnakeY(), getSnakeX()-1, snake);
              }
              else if (keyCode == KeyEvent.VK_RIGHT) {
                System.out.println("Right Arrow-Key is Released!");
                dealWithKeyPress(getSnakeY(), getSnakeX()+1, snake);
              }
            }
          });
    }
    
    public static void dealWithKeyPress(int snake_y, int snake_x, LinkedList<snakeObj> snake) {
    	snakeObj body0 = new snakeObj();
        body0.addSnake(getSnakeY(), getSnakeX());
        snake.addFirst(body0);
        
        //If snake collides with wall, 
        if(snake_x < 0 || snake_x > 19 || snake_y < 0 || snake_y > 19) {
        	System.out.println("You crashed into a wall, Game Over!");
        	endGame(snake);
        
        }else if(selfCrash(snake_y, snake_x)) {
        	System.out.println("You crashed into yourself, Game Over!");
        	endGame(snake);
        }else {
        	//grow snake if the snake collides with food
     	   if(foodCollision(snake_y, snake_x)) {
     		   handleFoodCollision(snake);
     	   }
            moveSnakeByKey(snake_y, snake_x);
            snake.removeLast();
            AddSnakeToBoard(snake);
            displayBoard();
        }
    }

	private static void endGame(LinkedList<snakeObj> snake) {
		System.out.println("Your snake reached length " + (int)(snake.size()-1));
		System.exit(0);
	}

	private static void handleFoodCollision(LinkedList<snakeObj> snake) {
		   growSnake(snake);
		   foodCounter++;
		   spawnFood();
	}
    
    public static boolean selfCrash(int snake_y, int snake_x) {
    	return maze[snake_y][snake_x] == ".";
    }
    
    public static boolean foodCollision(int snake_y, int snake_x) {
    	return maze[snake_y][snake_x] == "F";
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
    
    private static void spawnFood() {
    	Random rand = new Random();
    	boolean whenFoodPlaced = false;
    	while(!whenFoodPlaced) {
    		int randX = rand.nextInt(size);
        	int randY = rand.nextInt(size);
    		if (maze[randX][randY] == "0") {
    			maze[randX][randY] = "F";
    			foodRows = randX;
    			foodCols = randY;
    			whenFoodPlaced = true;
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
        
        //this code makes it so it does not grow indefinitely
        snakeObj lastBodySegment = snake.getLast();
        maze[lastBodySegment.y][lastBodySegment.x] = "0";
    }
    
    public static void growSnake(LinkedList<snakeObj> snake) {
    	
    	snakeObj lastBodySegment = snake.getLast();
    	
    	snakeObj newBodySegment = new snakeObj();
    	newBodySegment.addSnake(lastBodySegment.y, lastBodySegment.x);
    	
    	snake.add(newBodySegment);
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


class snakeObj {     //started coding at 1pm
    int x;
    int y;
    String type = ".";
    void addSnake(int Y, int X){
        x=X;
        y=Y;
    }
}

