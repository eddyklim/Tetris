/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package colorthemes;

import java.awt.Color;
import model.Block;

/**
 * Holds all the standard colors for the blocks.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public class StandardTheme extends AbstractTheme {

    @Override
    public Color getColor(final Block theBlock) {
        Color aColor = Color.BLACK;
        if (theBlock.equals(Block.O)) {
            aColor = Color.YELLOW;
        }
        if (theBlock.equals(Block.I)) {
            aColor = Color.CYAN;
        }
        if (theBlock.equals(Block.J)) {
            aColor = Color.BLUE;
        }
        if (theBlock.equals(Block.L)) {
            aColor = Color.ORANGE;
        }
        if (theBlock.equals(Block.S)) {
            aColor = Color.GREEN;
        }
        if (theBlock.equals(Block.Z)) {
            aColor = Color.RED;
        }
        if (theBlock.equals(Block.T)) {
            aColor = Color.MAGENTA;
        }
        return aColor;
    }
}
