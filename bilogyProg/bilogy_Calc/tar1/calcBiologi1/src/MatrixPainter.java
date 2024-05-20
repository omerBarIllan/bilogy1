import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MatrixPainter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Matrix Painter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.add(new MatrixPanel(10, 10));
            frame.setVisible(true);
        });
    }
}

class MatrixPanel extends JPanel {
    private static final int CELL_SIZE = 30;
    private final int rows;
    private final int cols;
    private final Color[][] matrix;

    public MatrixPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new Color[rows][cols];
        initializeMatrix();

    }

    private void initializeMatrix() {
        Random rand = new Random();
        int totalCells = rows * cols;
        int blackCells = totalCells / 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Color.WHITE;
            }
        }

        for (int i = 0; i < blackCells; i++) {
            int row, col;
            do {
                row = rand.nextInt(rows);
                col = rand.nextInt(cols);
            } while (matrix[row][col] == Color.BLACK);
            matrix[row][col] = Color.BLACK;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g.setColor(matrix[i][j]);
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(cols * CELL_SIZE, rows * CELL_SIZE);
    }
}
