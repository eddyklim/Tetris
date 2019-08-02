/*
 * TCSS 305 – Fall 2017 Assignment 6 - Tetris
 */

package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

/**
 * Display that allows the user to set the size of the Board.
 * 
 * @author eduardk
 * @version 5 Dec, 2017
 *
 */
public class SetBoardSizePanel extends JPanel {
    /** The serialVersionUID. */
    private static final long serialVersionUID = -797543190917452287L;
    /** The default board width. */
    private static final int DEFAULT_WIDTH = 10;
    /** The default board height. */
    private static final int DEFAULT_HEIGHT = 20;
    /** The slider major tick. */
    private static final int SPACING = 5;
    /** The board width. */
    private int myWidth;
    /** The board height. */
    private int myHeight;
    
    /** Default constructor that initializes the ScoreBoard. */
    SetBoardSizePanel() {
        super();
        myWidth = DEFAULT_WIDTH;
        myHeight = DEFAULT_HEIGHT;
        setUpPanel();
    }

    /** Sets up sliders and preview image. */
    private void setUpPanel() {
        setLayout(new BorderLayout());
        
        final JSlider xSlider = new JSlider(JSlider.HORIZONTAL, 5, 25, 10);
        xSlider.setPaintTicks(true);
        xSlider.setPaintLabels(true);
        xSlider.setMinorTickSpacing(1);
        xSlider.setMajorTickSpacing(SPACING);
        xSlider.setBorder(new EmptyBorder(SPACING, 0, 0, 0));
        xSlider.addChangeListener((theEvent) -> {
            myWidth = xSlider.getValue();
            repaint();
        });

        final JSlider ySlider = new JSlider(JSlider.VERTICAL, 10, 25, 20);
        ySlider.setPaintTicks(true);
        ySlider.setPaintLabels(true);
        ySlider.setMinorTickSpacing(1);
        ySlider.setMajorTickSpacing(SPACING);
        ySlider.setBorder(new EmptyBorder(0, 0, 0, SPACING));
        ySlider.addChangeListener((theEvent) -> {
            myHeight = ySlider.getValue();
            repaint();
        });
        
        final JLabel tetrisPreview = new JLabel(new ImageIcon("./images/preview.png"));
        add(xSlider, BorderLayout.SOUTH);
        add(ySlider, BorderLayout.WEST);
        add(tetrisPreview, BorderLayout.CENTER);
    }
    
    /**
     * Getter for the board width.
     * 
     * @return The width.
     */
    public int getBoardWidth() {
        return myWidth;
    }
    
    /**
     *  Getter for the board height.
     * 
     * @return The height.
     */
    public int getBoardHeight() {
        return myHeight;
    }
}
