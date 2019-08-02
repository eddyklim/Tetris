/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package colorthemes;

import java.awt.Color;
import model.Block;

/**
 * Holds all the warm colors for the blocks.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public class WarmTheme extends AbstractTheme {

    /** The a color of the warm theme. */
    private static final Color WARM_COLOR_1 = new Color(220, 20, 60);
    /** The a color of the warm theme. */
    private static final Color WARM_COLOR_2 = new Color(255, 127, 80);
    /** The a color of the warm theme. */
    private static final Color WARM_COLOR_3 = new Color(255, 165, 0);
    /** The a color of the warm theme. */
    private static final Color WARM_COLOR_4 = new Color(255, 215, 0);
    /** The a color of the warm theme. */
    private static final Color WARM_COLOR_5 = new Color(240, 230, 140);
    /** The a color of the warm theme. */
    private static final Color WARM_COLOR_6 = new Color(255, 105, 180);
    /** The a color of the warm theme. */
    private static final Color WARM_COLOR_7 = new Color(255, 192, 203);
    
    @Override
    public Color getColor(final Block theBlock) {
        Color aColor = Color.BLACK;
        if (theBlock.equals(Block.O)) {
            aColor = WARM_COLOR_1;
        }
        if (theBlock.equals(Block.I)) {
            aColor = WARM_COLOR_2;
        }
        if (theBlock.equals(Block.J)) {
            aColor = WARM_COLOR_3;
        }
        if (theBlock.equals(Block.L)) {
            aColor = WARM_COLOR_4;
        }
        if (theBlock.equals(Block.S)) {
            aColor = WARM_COLOR_5;
        }
        if (theBlock.equals(Block.Z)) {
            aColor = WARM_COLOR_6;
        }
        if (theBlock.equals(Block.T)) {
            aColor = WARM_COLOR_7;
        }
        return aColor;
    }
}
