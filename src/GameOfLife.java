import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    public GameOfLife() {
        add(new GameOfLifeGrid());
        pack();
        setTitle("Game of Life");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameOfLife().setVisible(true);
            }
        });
    }
}
