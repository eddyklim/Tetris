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
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Block;
import model.TetrisPiece;

/**
 * Shows the next tetris piece to fall.
 * 
 * @author eduardk
 * @version 1 Dec, 2017
 *
 */
public class PreviewBoard extends JPanel implements Observer {
    /** The serialVersionUID. */
    private static final long serialVersionUID = 5761028974341798603L;
    /** The size of the block. */
    private static final int BLOCK_SIZE = 40;
    /** The horizontal shift. */
    private static final int XSHIFT = 60;
    /** The the vertical shift. */
    private static final int YSHIFT = 140;
    /** The I piece shift. */
    private static final int ISHIFT = 20;
    /** The inner square shift. */
    private static final int BLACK_SHIFT = 5;
    /** The inner square width. */
    private static final int BLACK_WIDTH = 9;
    /** The smaller square shift. */
    private static final int CORNER_SHIFT = 21;
    /** The smaller square width. */
    private static final int CORNER_WIDTH = 25;
    /** The width of the panel. */
    private static final int SIDE_PANEL_WIDTH = 240;
    /** The height of the panel. */
    private static final int PREVIEW_PANEL_HEIGHT = 200;
    /** The points to draw. */
    private model.Point[] myPoints;
    /** The the block. */
    private Block myBlock;
    /** The next piece. */
    private TetrisPiece myPiece;
    /** The x shift used to center the piece. */
    private int myXShift;
    /** The y shift used to center the piece. */
    private int myYShift;
    /** The theme of the game. */
    private AbstractTheme myTheme;
    
    /**
     * Default constructor that initializes the PreviewBoard.
     * 
     * @param theNextPiece The next piece to fall.
     * @param theTheme The theme to color the blocks.
     */
    public PreviewBoard(final TetrisPiece theNextPiece, final AbstractTheme theTheme) {
        super();
        myPoints = theNextPiece.getPoints().clone();
        myBlock = theNextPiece.getBlock();
        myPiece = theNextPiece;
        myXShift = XSHIFT;
        myYShift = YSHIFT;
        setUpPanel();
        myTheme = theTheme;
    }
    
    /**
     * Helper method that sets up the size of the panel, background color, and the
     * border.
     */
    private void setUpPanel() {
        setPreferredSize(new Dimension(SIDE_PANEL_WIDTH, PREVIEW_PANEL_HEIGHT));
        setMaximumSize(new Dimension(SIDE_PANEL_WIDTH, PREVIEW_PANEL_HEIGHT));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createMatteBorder(0, 2 * 2, 2, 0, Color.DARK_GRAY.darker()));
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(1));

        myBlock = myPiece.getBlock();
        myPoints = myPiece.getPoints().clone();
        
        myXShift = XSHIFT;
        myYShift = YSHIFT;
        
        if (myBlock.equals(Block.O)) {
            myXShift = XSHIFT - ISHIFT;
        }
        if (myBlock.equals(Block.I)) {
            myXShift = XSHIFT - ISHIFT;
            myYShift = YSHIFT + ISHIFT;
        }
        
        final Color aColor = myTheme.getColor(myBlock);
        
        for (int i = 0; i < myPoints.length; i++) {
            g2d.setPaint(aColor);
            final model.Point point = myPoints[i];
            g2d.fill(new Rectangle2D.Double(point.x() * BLOCK_SIZE + myXShift,
                                   (point.y() * -BLOCK_SIZE) + myYShift, 
                                   BLOCK_SIZE, BLOCK_SIZE));
            
            
            g2d.setPaint(Color.BLACK);
            g2d.fill(new Rectangle2D.Double(point.x() * BLOCK_SIZE + myXShift + BLACK_SHIFT ,
                                         (point.y() * -BLOCK_SIZE) + myYShift + BLACK_SHIFT , 
                                         BLOCK_SIZE - BLACK_WIDTH, BLOCK_SIZE - BLACK_WIDTH));

            g2d.setPaint(aColor);
            g2d.fill(new Rectangle2D.Double(point.x() * BLOCK_SIZE + myXShift + CORNER_SHIFT,
                                      (point.y() * -BLOCK_SIZE) + myYShift + CORNER_SHIFT, 
                                      BLOCK_SIZE - CORNER_WIDTH , BLOCK_SIZE - CORNER_WIDTH));
            
            g2d.setPaint(aColor.darker().darker().darker());
            g2d.draw(new Rectangle2D.Double(point.x() * BLOCK_SIZE + myXShift,
                                            (point.y() * -BLOCK_SIZE) + myYShift, 
                                            BLOCK_SIZE, BLOCK_SIZE));
            g2d.setPaint(aColor.darker());
            g2d.draw(new Rectangle2D.Double(point.x() * BLOCK_SIZE + myXShift + 1,
                                            (point.y() * -BLOCK_SIZE) + myYShift + 1, 
                                            BLOCK_SIZE - 2, BLOCK_SIZE - 2));
        }
    }

    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            myPiece = (TetrisPiece) theArg;
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
