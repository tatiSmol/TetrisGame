package blocks;

import tetrisgame.TetrisBlock;

import java.awt.*;

public class ShapeZ extends TetrisBlock {
    public ShapeZ() {
        super(new int[][]{{1, 1, 0}, {0, 1, 1}});
        this.setColor(Color.RED);
    }
}
