package blocks;

import tetrisgame.TetrisBlock;

import java.awt.*;

public class ShapeL extends TetrisBlock {
    public ShapeL() {
        super(new int[][]{{1, 0}, {1, 0}, {1, 1}});
        this.setColor(new Color(224, 97, 17));
    }
}
