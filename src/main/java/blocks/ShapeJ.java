package blocks;

import tetrisgame.TetrisBlock;

import java.awt.*;

public class ShapeJ extends TetrisBlock {
    public ShapeJ() {
        super(new int[][]{{0, 1}, {0, 1}, {1, 1}});
        this.setColor(Color.BLUE);
    }
}
