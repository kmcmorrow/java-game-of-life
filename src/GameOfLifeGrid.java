import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifeGrid extends JPanel implements ActionListener {
    private final int FPS = 30;

    private int cellSize = 4;
    private int width = 200;
    private int height = 200;

    private boolean[][] grid;
    private boolean[][] updatedGrid;

    public GameOfLifeGrid() {
        setPreferredSize(new Dimension(cellSize * width, cellSize * height));

        grid = new boolean[height][width];
        updatedGrid = new boolean[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (Math.random() < 0.5) {
                    grid[row][col] = true;
                }
            }
        }

        Timer timer = new Timer(1000/FPS, this);
        timer.start();
    }

    private void draw(Graphics g) {
        setBackground(Color.darkGray);
        g.setColor(Color.orange);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (grid[row][col]) {
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private int getNumberOfNeighbours(int cellRow, int cellCol) {
        int number = 0;
        for (int row = cellRow - 1; row <= cellRow + 1; row++) {
            for (int col = cellCol - 1; col <= cellCol + 1; col++) {
                if (row == cellRow && col == cellCol) continue;
                if (row < 0 || col < 0 || row >= height || col >= width) continue;
                if (grid[row][col]) {
                    number++;
                }
            }
        }
        return number;
    }

    private void update() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int numberOfNeighbours = getNumberOfNeighbours(row, col);
                if (grid[row][col]) { // alive
                    updatedGrid[row][col] = numberOfNeighbours == 2 || numberOfNeighbours == 3;
                }
                else { // dead
                    updatedGrid[row][col] = numberOfNeighbours == 3;
                }
            }
        }
        swapGrids();

        repaint();
    }

    private void swapGrids() {
        boolean[][] tmpGrid = grid;
        grid = updatedGrid;
        updatedGrid = tmpGrid;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
