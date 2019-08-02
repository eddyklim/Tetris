/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package colorthemes;

import java.awt.Color;
import model.Block;

/**
 * Holds all the spring colors for the blocks.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public class SpringTheme extends AbstractTheme {

    /** The a color of the spring theme. */
    private static final Color SPRING_COLOR_1 = new Color(0, 255, 127);
    /** The a color of the spring theme. */
    private static final Color SPRING_COLOR_2 = new Color(60, 179, 113);
    /** The a color of the spring theme. */
    private static final Color SPRING_COLOR_3 = new Color(102, 205, 170);
    /** The a color of the spring theme. */
    private static final Color SPRING_COLOR_4 = new Color(0, 255, 0);
    /** The a color of the spring theme. */
    private static final Color SPRING_COLOR_5 = new Color(124, 222, 0);
    /** The a color of the spring theme. */
    private static final Color SPRING_COLOR_6 = new Color(173, 255, 47);
    /** The a color of the spring theme. */
    private static final Color SPRING_COLOR_7 = new Color(127, 255, 212);
    
    @Override
    public Color getColor(final Block theBlock) {
        Color aColor = Color.BLACK;
        if (theBlock.equals(Block.O)) {
            aColor = SPRING_COLOR_1;
        }
        if (theBlock.equals(Block.I)) {
            aColor = SPRING_COLOR_2;
        }
        if (theBlock.equals(Block.J)) {
            aColor = SPRING_COLOR_3;
        }
        if (theBlock.equals(Block.L)) {
            aColor = SPRING_COLOR_4;
        }
        if (theBlock.equals(Block.S)) {
            aColor = SPRING_COLOR_5;
        }
        if (theBlock.equals(Block.Z)) {
            aColor = SPRING_COLOR_6;
        }
        if (theBlock.equals(Block.T)) {
            aColor = SPRING_COLOR_7;
        }
        return aColor;
    }
}
