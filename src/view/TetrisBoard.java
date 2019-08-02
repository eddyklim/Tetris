/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package view;

import colorthemes.AbstractTheme;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Block;
import model.Board;

/**
 * Shows tetris board for the game.
 * 
 * @author eduardk
 * @version 1 Dec, 2017
 *
 */
public class TetrisBoard extends JPanel implements Observer {
    /** The serialVersionUID. */
    private static final long serialVersionUID = 5761028974341798603L;
    /** The block size. */
    private static final int BLOCK_SIZE = 40;
    /** The inner block shift. */
    private static final int BLACK_SHIFT = 5;
    /** The inner block width. */
    private static final int BLACK_WIDTH = 9;
    /** The smaller block shift. */
    private static final int CORNER_SHIFT = 20;
    /** The smaller block width. */
    private static final int CORNER_WIDTH = 24;
    /** The game board. */
    private final Board myBoard;
    /** The list of all spaced on the grid. */
    private List<?> myList;
    /** The theme of the game. */
    private AbstractTheme myTheme;
    
    /**
     * Default constructor that initializes the TetrisBoard.
     * 
     * @param theBoard The game board.
     * @param theTheme The theme to color the blocks.
     */
    TetrisBoard(final Board theBoard,
                final AbstractTheme theTheme) {
        super();
//        final List<?> otherList = new ArrayList<Block[]>();
//        myList = theList;
        myBoard = theBoard;
        setPreferredSize(new Dimension(myBoard.getWidth() * BLOCK_SIZE,
                                         myBoard.getHeight() * BLOCK_SIZE));
        setBackground(Color.BLACK);
        myTheme = theTheme;
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(1));
        Color aColor = Color.BLACK;
        for (int i = myList.size() - 1; i >= 0; i--) {
            final Block[] row = (Block[]) myList.get(i);
            for (int j = 0; j < row.length; j++) {
                if (row[j] != null) {
                    aColor = myTheme.getColor(row[j]);
                    g2d.setPaint(aColor);
                    g2d.fill(new Rectangle2D.Double(j * BLOCK_SIZE, (i * -BLOCK_SIZE)
                                                    + (myBoard.getHeight() - 1) * BLOCK_SIZE,
                                                    BLOCK_SIZE, BLOCK_SIZE));
                    g2d.setPaint(Color.BLACK);
                    g2d.fill(new Rectangle2D.Double(j * BLOCK_SIZE + BLACK_SHIFT,
                                                    (i * -BLOCK_SIZE + BLACK_SHIFT)
                                                    + (myBoard.getHeight() - 1) * BLOCK_SIZE,
                                                    BLOCK_SIZE - BLACK_WIDTH, BLOCK_SIZE
                                                    - BLACK_WIDTH));
                    g2d.setPaint(aColor);
                    g2d.fill(new Rectangle2D.Double(j * BLOCK_SIZE + CORNER_SHIFT,
                                                    (i * -BLOCK_SIZE + CORNER_SHIFT)
                                                    + (myBoard.getHeight() - 1) * BLOCK_SIZE,
                                                    BLOCK_SIZE - CORNER_WIDTH,
                                                    BLOCK_SIZE - CORNER_WIDTH));
                    g2d.setPaint(aColor.darker());
                    g2d.draw(new Rectangle2D.Double(j * BLOCK_SIZE + 1, (i * -BLOCK_SIZE)
                                                  + (myBoard.getHeight() - 1) * BLOCK_SIZE + 1,
                                                  BLOCK_SIZE - 2, BLOCK_SIZE - 2));
                }
            }
        }
        g2d.setColor(Color.DARK_GRAY.darker().darker());
        for (int i = 0; i < myBoard.getWidth(); i++) {
            g2d.draw(new Line2D.Double(i * BLOCK_SIZE, 0, i
                                       * BLOCK_SIZE, myBoard.getHeight() * BLOCK_SIZE));
        }
        for (int i = 0; i < myBoard.getHeight(); i++) {
            g2d.draw(new Line2D.Double(0, i * BLOCK_SIZE,
                                       myBoard.getWidth() * BLOCK_SIZE, i * BLOCK_SIZE));
        } 
    }

    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof List<?>) {
            List<?> otherList = new ArrayList<Block[]>();
            otherList = (List<?>) theArg;
            myList = otherList;
            repaint();
        }
    }
    
    /**
     * Updates the theme to paint the blocks.
     * 
     * @param theTheme The new theme.
     */
    public void updateTheme(final AbstractTheme theTheme) {
        myTheme = theTheme;
        repaint();
    }
}