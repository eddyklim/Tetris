/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package colorthemes;

import java.awt.Color;
import model.Block;

/**
 * Holds all the cold colors for the blocks.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public class ColdTheme extends AbstractTheme {

    /** The a color of the cold theme. */
    private static final Color COLD_COLOR_1 = new Color(138, 43, 226);
    /** The a color of the cold theme. */
    private static final Color COLD_COLOR_2 = new Color(123, 104, 238);
    /** The a color of the cold theme. */
    private static final Color COLD_COLOR_3 = new Color(0, 0, 158);
    /** The a color of the cold theme. */
    private static final Color COLD_COLOR_4 = new Color(72, 61, 169);
    /** The a color of the cold theme. */
    private static final Color COLD_COLOR_5 = new Color(128, 0, 128);
    /** The a color of the cold theme. */
    private static final Color COLD_COLOR_6 = new Color(0, 0, 255);
    /** The a color of the cold theme. */
    private static final Color COLD_COLOR_7 = new Color(70, 130, 180);

    @Override
    public Color getColor(final Block theBlock) {
        Color aColor = Color.BLACK;
        if (theBlock.equals(Block.O)) {
            aColor = COLD_COLOR_1;
        }
        if (theBlock.equals(Block.I)) {
            aColor = COLD_COLOR_2;
        }
        if (theBlock.equals(Block.J)) {
            aColor = COLD_COLOR_3;
        }
        if (theBlock.equals(Block.L)) {
            aColor = COLD_COLOR_4;
        }
        if (theBlock.equals(Block.S)) {
            aColor = COLD_COLOR_5;
        }
        if (theBlock.equals(Block.Z)) {
            aColor = COLD_COLOR_6;
        }
        if (theBlock.equals(Block.T)) {
            aColor = COLD_COLOR_7;
        }
        return aColor;
    }
}
