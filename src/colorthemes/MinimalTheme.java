/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package colorthemes;

import java.awt.Color;
import model.Block;

/**
 * Holds all the minimal colors for the blocks.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public class MinimalTheme extends AbstractTheme {

    @Override
    public Color getColor(final Block theBlock) {
        return Color.WHITE;
    }
}
