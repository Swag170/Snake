import javax.swing.*;
import java.awt.*;

public class ColorGrid {
    static int[][] board  = new int[10][10];

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            //int[][] board  = new int[10][10];

            for(int i=0; i< board.length; i++){
                for(int j=0; j< board.length; j++){
                    board[i][j] = (int) (Math.random() *3);
                }
            }

            JFrame frame = new JFrame("Snake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(board.length, board.length)); // Adjust the grid size as needed

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    JPanel square = new JPanel();
                    //square.setBackground((i + j) % 2 == 0 ? Color.BLACK : Color.WHITE);
                    if(board[i][j] == 0)square.setBackground(Color.GREEN);
                    if(board[i][j] == 1)square.setBackground(Color.red);
                    if(board[i][j] == 2)square.setBackground(Color.blue);
                    //square.setSize(30, 30);
                    panel.add(square);
                }
            }

            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
            frame.setSize(600,600);  
        });
    }
}
