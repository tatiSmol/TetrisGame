package tetrisgame;

import java.awt.*;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape;
    private int [][][] shapes;
    private Color color;
    private int x, y;
    private int currentRotation;

    public TetrisBlock(int[][] shape) {
        this.shape = shape;
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
        Random random = new Random();
        currentRotation = random.nextInt(shapes.length);
        shape = shapes[currentRotation];
        x = random.nextInt(gridWidth - getWidth());
        y = -getHeight();
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
