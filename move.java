import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.util.Random;

public class move extends JFrame implements KeyListener {

    private char[][] board;
    private boolean foodSpawned = false;
    private int snakeX = 0;
    private int snakeY = 0;
    private int foodX;
    private int foodY;
    int rows = 10;
	int cols = 10;

    public move() {
   
        board = new char[rows][cols];
        makeBoard();
        spawnFood();
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.addKeyListener(this);

        add(textArea);

        setTitle("Key Pressed Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void makeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '0';
            }
        }
        board[snakeX][snakeY] = 'X';
        board[foodX][foodY] = 'F';
    }
    private void spawnFood() {
    	Random rand = new Random();
    	while(true) {
    		int randX = rand.nextInt(rows);
        	int randY = rand.nextInt(cols);
    		if (board[randX][randY] == '0') {
    			board[randX][randY] = 'F';
    			foodX = randX;
    			foodY = randY;
    			break;
    		}
    	}
    }

    private void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new move();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // Clear the console
        System.out.println("");
        switch (keyCode) {
            case 38:
                if (snakeX > 0) {
                    snakeX--;
                }
                break;
            case 40:
                if (snakeX < 9) {
                    snakeX++;
                }
                break;
            case 37:
                if (snakeY > 0) {
                    snakeY--;
                }
                break;
            case 39:
                if (snakeY < 9) {
                    snakeY++;
                }
                break;
        }
        if(foodX == snakeX && foodY == snakeY) { //When head of snake goes over the food, new food spawns
        	spawnFood();
        }
        makeBoard();
        displayBoard();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}
