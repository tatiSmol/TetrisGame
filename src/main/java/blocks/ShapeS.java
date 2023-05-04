package blocks;

import tetrisgame.TetrisBlock;

import java.awt.*;

public class ShapeS extends TetrisBlock {
    public ShapeS() {
        super(new int[][]{{0, 1, 1}, {1, 1, 0}});
        this.setColor(Color.GREEN);
    }
}
