import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    private int gridRows, gridColumns, gridCellSize;
    private TetrisBlock block;


    public GameArea(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;

        spawnBlock();
    }

    public void spawnBlock() {
        block = new TetrisBlock(new int[][] {{1, 0}, {1, 0}, {1, 1}},
                Color.BLUE);
        block.spawn(gridColumns);
    }

    private void drawBlock(Graphics g) {
        int h = block.getHeight();
        int w = block.getWidth();
        int[][] sh = block.getShape();
        Color c = block.getColor();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (sh[row][col] == 1) {
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;

                    g.setColor(c);
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, gridCellSize, gridCellSize);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBlock(g);
    }

    public void moveBlockDown() {
        if (!checkBottom()) {
            return;
        }

        block.moveDown();
        repaint();
    }

    private boolean checkBottom() {
        return block.getBottomEdge() != gridRows;
    }
}
