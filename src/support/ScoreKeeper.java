/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package support;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.TetrisPiece;

/**
 * Keeps the score for the game. The scoring algorithm is based on NES Tetris.
 * 
 * @author eduardk
 * @version 1 Dec, 2017
 *
 */
public class ScoreKeeper extends JPanel implements Observer {
    
    private static final long serialVersionUID = 9217485070763872584L;
    /** The amount of lines that need to be cleared to level up. */
    private static final int LINES_TO_LEVEL_UP = 5;
    /** The one line cleared multiplier. */
    private static final int ONE_LINE_MULTIPLIER = 40;
    /** The two lines cleared multiplier. */
    private static final int TWO_LINE_MULTIPLIER = 100;
    /** The three lines cleared multiplier. */
    private static final int THREE_LINE_MULTIPLIER = 300;
    /** The four lines cleared multiplier. */
    private static final int FOUR_LINE_MULTIPLIER = 1200;
    /** The three cleared lines amount. */
    private static final int THREE_LINES_CLEARED = 3;
    /** The four cleared lines amount. */
    private static final int FOUR_LINES_CLEARED = 4;
    /** The amount of points per block. */
    private static final int POINTS_PER_BLOCK = 4;
    /** The score set when a new game begins. */
    private static final int RESET_SCORE_VALUE = -8;
    /** Used for parsing the string. */
    private static final String SCORE_DIVIS0R = "/";
    /** Total rows cleared. */
    private int myRowsCleared;
    /** Total score. */
    private int myScore;
    /** Lines to level. */
    private int myToNextLevel;
    /** Current level. */
    private int myLevel;
    /** Rows cleared. */
    private Integer[] myArray;
    /** Row cleared in int. */
    private int myArrayLength;
    
    /**
     * Default constructor that initializes the ScoreKeeper. The game starts at
     * level one and requires 5 lines to be cleared before moving to the next level.
     */
    public ScoreKeeper() {
        super();
        myRowsCleared = 0;
        myScore = -POINTS_PER_BLOCK;
        myLevel = 1;
        myToNextLevel = LINES_TO_LEVEL_UP;
        myArray = new Integer[LINES_TO_LEVEL_UP];
        myArrayLength = myArray.length;
    }
    
    /** Calculates the score just as the NES version of Tetris. */
    private void calculateScore() {
        while (myArrayLength != 0) {
            myArrayLength--;
            if (myToNextLevel <= 0) {
                myToNextLevel = LINES_TO_LEVEL_UP + myToNextLevel;
                myLevel++;
                firePropertyChange("level up", false, true);
            }
        }
        
        if (myArray.length == 1) {
            myScore += ONE_LINE_MULTIPLIER * myLevel;
        } else if (myArray.length == 2) {
            myScore += TWO_LINE_MULTIPLIER * myLevel;
        } else if (myArray.length == THREE_LINES_CLEARED) {
            myScore += THREE_LINE_MULTIPLIER * myLevel;
        } else if (myArray.length == FOUR_LINES_CLEARED) {
            myScore += FOUR_LINE_MULTIPLIER * myLevel;
        }
    }

    /** Resets the labels for a new game. */
    public void resetScore() {
        myRowsCleared = 0;
        myScore = RESET_SCORE_VALUE;
        myLevel = 1;
        myToNextLevel = LINES_TO_LEVEL_UP;
        myArray = new Integer[LINES_TO_LEVEL_UP];
        myArrayLength = 0;
    }
    
    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof Integer[]) {
            myArray = (Integer[]) theArg;
            myRowsCleared += myArray.length;
            myToNextLevel -= myArray.length;
            myArrayLength = myArray.length;
            calculateScore();
        } else if (theArg instanceof TetrisPiece) {
            myScore += POINTS_PER_BLOCK;
        }
    }
    
    @Override
    public String toString() {
        return myScore + SCORE_DIVIS0R + myLevel + SCORE_DIVIS0R
                        + myRowsCleared + SCORE_DIVIS0R + myToNextLevel;
    }
}
