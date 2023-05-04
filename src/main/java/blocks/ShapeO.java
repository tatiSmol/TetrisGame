package blocks;

import tetrisgame.TetrisBlock;

import java.awt.*;

public class ShapeO extends TetrisBlock {
    public ShapeO() {
        super(new int[][]{{1, 1}, {1, 1}});
        this.setColor(Color.YELLOW);
    }
}
