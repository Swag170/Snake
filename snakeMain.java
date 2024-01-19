package snake;

public class Main {
	static int size = 20;
	static String [][] maze = new String[size][size];
	public static void main(String[] args) {
makeBoard();
addSnake();
displayBoard();
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
	public static void displayBoard() {
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            System.out.print(maze[i][j] + " ");
	        }
	        System.out.println();
	    }
	}
}

