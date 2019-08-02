/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package colorthemes;

import java.awt.Color;
import model.Block;

/**
 * Holds all the colors for the blocks.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public abstract class AbstractTheme {

    /**
     * Returns a color based on the block.
     * 
     * @param theBlock The tetris block.
     * @return The color of the block.
     */
    public abstract Color getColor(Block theBlock);
}
