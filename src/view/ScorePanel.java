/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import support.ScoreKeeper;

/**
 * Shows the next score board for the game.
 * 
 * @author eduardk
 * @version 1 Dec, 2017
 *
 */
public class ScorePanel extends JPanel implements Observer {
    /** The serialVersionUID. */
    private static final long serialVersionUID = -6673812807218834221L;
    /** The font size. */
    private static final int FONT_SIZE = 18;
    /** The width of the panel. */
    private static final int SIDE_PANEL_WIDTH = 240;
    /** The height of the panel. */
    private static final int SCORE_PANEL_HEIGHT = 135;
    /** The text color. */
    private static final Color THE_TEXT_COLOR = Color.WHITE;
    /** The font. */
    private static final String THE_FONT = "Default";
    /** The int is used in an array so the fourth element will [3]. */
    private static final int THE_FOURTH_SCORE = 3;
    /** The score label. */
    private JLabel myScoreLabel;
    /** The level label. */
    private JLabel myLevelLabel;
    /** The cleared label. */
    private JLabel myClearedLabel;
    /** The to level up label. */
    private JLabel myToLevelLabel;
    /** The score keeper. */
    private final ScoreKeeper myScoreTracker;
    
    /**
     * Default constructor that initializes the ScoreBoard.
     * 
     * @param theKeeper Tracks the score.
     */
    public ScorePanel(final ScoreKeeper theKeeper) {
        super();
        addLabels();
        setPreferredSize(new Dimension(SIDE_PANEL_WIDTH, SCORE_PANEL_HEIGHT));
        setMaximumSize(new Dimension(SIDE_PANEL_WIDTH, SCORE_PANEL_HEIGHT));
        setBackground(Color.BLACK);
        myScoreTracker = theKeeper;
        setBorder(BorderFactory.createMatteBorder(2, 2 * 2, 0, 0, Color.DARK_GRAY.darker()));
        
    }
    
    /** Sets up the different labels. */
    private void addLabels() {
        setLayout(new FlowLayout());
        myScoreLabel = new JLabel("Total Score: 0");
        myLevelLabel = new JLabel("Current Level: 1");
        myClearedLabel = new JLabel("Lines Cleared: 0");
        myToLevelLabel = new JLabel("Lines To Next Level: 5");
        
        myScoreLabel.setForeground(THE_TEXT_COLOR);
        myLevelLabel.setForeground(THE_TEXT_COLOR);
        myClearedLabel.setForeground(THE_TEXT_COLOR);
        myToLevelLabel.setForeground(THE_TEXT_COLOR);
        
        myScoreLabel.setFont(new Font(THE_FONT, Font.PLAIN, FONT_SIZE)); 
        myLevelLabel.setFont(new Font(THE_FONT, Font.PLAIN, FONT_SIZE)); 
        myClearedLabel.setFont(new Font(THE_FONT, Font.PLAIN, FONT_SIZE)); 
        myToLevelLabel.setFont(new Font(THE_FONT, Font.PLAIN, FONT_SIZE)); 
        
        add(myScoreLabel);
        add(myLevelLabel);
        add(myClearedLabel);
        add(myToLevelLabel);
    }
    
    @Override
    public void update(final Observable theO, final Object theArg) {
        final String[] parts = myScoreTracker.toString().split("/");
        myScoreLabel.setText("Total Score: " + parts[0]);
        myLevelLabel.setText("Current Level: " + parts[1]);
        myClearedLabel.setText("Lines Cleared: " + parts[2]);
        myToLevelLabel.setText("Lines To Next Level: " + parts[THE_FOURTH_SCORE]);
    }
}
