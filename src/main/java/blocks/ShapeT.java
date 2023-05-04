package blocks;

import tetrisgame.TetrisBlock;

import java.awt.*;

public class ShapeT extends TetrisBlock {
    public ShapeT() {
        super(new int[][]{{1, 1, 1}, {0, 1, 0}});
        this.setColor(Color.MAGENTA);
    }
}
