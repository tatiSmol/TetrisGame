import java.awt.*;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public void spawn(int gridWidth) {
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

    public void moveDown() {
        y++;
    }

    public void moveRight() {
        x++;
    }

    public void moveLeft() {
        x--;
    }
}
