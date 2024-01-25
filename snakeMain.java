package snake;

import java.util.Scanner;

<<<<<<< SnakeMain
public class Main {
	static int size = 20;
	static String [][] maze = new String[size][size];
	static int snakeRow = size/2;
	static int snakeCol = size/2;
	static String direction = "d"; // initial direction
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		makeBoard();
		addSnake();
		new Thread(() -> {
			while(true) {
				direction = scanner.next();
			}
		}).start();
		while(true) {
			displayBoard();
			moveSnake();
			try {
				Thread.sleep(500); // pause for 500 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
		for (int i = 0; i < snakeSize; i++) {
			maze[snakeRow][snakeCol + i] = "S";
		}
	}

	public static void moveSnake() {
		// clear current position
		maze[snakeRow][snakeCol] = "0";
		switch(direction) {
			case "w":
				snakeRow--;
				break;
			case "a":
				snakeCol--;
				break;
			case "s":
				snakeRow++;
				break;
			case "d":
				snakeCol++;
				break;
		}
		// update new position
		maze[snakeRow][snakeCol] = "S";
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
