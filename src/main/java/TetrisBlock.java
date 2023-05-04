import java.awt.*;

public class TetrisBlock {
    private int[][] shape;
    private int [][][] shapes;
    private Color color;
    private int x, y;
    private int currentRotation;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        initShapes();
    }

    private void initShapes() {
        shapes = new int[4][x][x];
        for (int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;
            shapes[i] = new int[r][c];

            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    shapes[i][y][x] = shape[c - x - 1][y];
                }
            }
            shape = shapes[i];
        }
    }

    public void spawn(int gridWidth) {
        currentRotation = 0;
        shape = shapes[currentRotation];
        x = (gridWidth - getWidth()) / 2;
        y = -getHeight();
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getBottomEdge() {
        return y + getHeight();
    }

    public int getLeftEdge() {
        return x;
    }

    public int getRightEdge() {
        return x + getWidth();
    }

    public void moveDown() {
        y++;
    }

    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }

    public void moveRight() {
        x++;
    }

    public void moveLeft() {
        x--;
    }
}
