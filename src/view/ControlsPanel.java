/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays key binding information to the user.
 * 
 * @author eduardk
 * @version 1 Dec, 2017
 *
 */
public class ControlsPanel extends JPanel implements Observer {
    /** The serialVersionUID. */
    private static final long serialVersionUID = -2152335552532713169L;
    /** The font size. */
    private static final int FONT_SIZE = 18;
    /** The width of the panel. */
    private static final int SIDE_PANEL_WIDTH = 240;
    /** The height of the panel. */
    private static final int SCORE_PANEL_HEIGHT = 260;
    /** The font. */
    private static final String THE_FONT = "Default";
    /** The index related to step function. */
    private static final int STEP_INDEX = 3;
    /** The index related to drop function. */
    private static final int DROP_INDEX = 4;
    /** The index related to pause function. */
    private static final int PAUSE_INDEX = 5;
    /** The index related to resume function. */
    private static final int RESUME_INDEX = 6;
    /** The label. */
    private final JLabel myLabel;
    /** The list of controls. */
    private final List<String> myList;

    /** 
     * Default constructor that initializes the ControlBoard.
     * 
     * @param theList The list of keys.
     */
    ControlsPanel(final List<String> theList) {
        super();
        myList = theList;
        myLabel = new JLabel();
        add(myLabel);
        updateControls();
        setBackground(Color.BLACK);
        myLabel.setForeground(Color.WHITE);
        myLabel.setFont(new Font(THE_FONT, Font.PLAIN, FONT_SIZE)); 
        setBorder(BorderFactory.createMatteBorder(2, 2 * 2, 2, 0, Color.DARK_GRAY.darker()));
        setPreferredSize(new Dimension(SIDE_PANEL_WIDTH, SCORE_PANEL_HEIGHT));
    }

    /**
     * This method updates new key binding information for the user.
     */
    private void updateControls() {
        final StringBuilder sb = new StringBuilder(200);
        sb.append("Current Controls:\nRotate Key: \u2191 or " + myList.get(0));
        sb.append("\nRight Key: \u2192 or " + myList.get(1));
        sb.append("\nLeft Key: \u2190 or " + myList.get(2));
        sb.append("\nDown Key: \u2193 or " + myList.get(STEP_INDEX));
        sb.append("\nDrop Key: " + myList.get(DROP_INDEX));
        sb.append("\nPause Key: " + myList.get(PAUSE_INDEX));
        sb.append("\nResume Key: " + myList.get(RESUME_INDEX));
        myLabel.setText("<html>" + sb.toString().replaceAll("\n", "<br>"));
    }

    @Override
    public void update(final Observable theO, final Object theArg) {
        updateControls();
    }
}
