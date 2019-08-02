/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package view;

import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.Board;
import support.TetrisKeyListener;

/**
 * The help menu which allows the user to look up scoring and set key bindings.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public class HelpMenu extends JMenu {
    /** The serialVersionUID. */
    private static final long serialVersionUID = -652286242890749183L;
    /** The index related to step function. */
    private static final int STEP_INDEX = 3;
    /** The index related to drop function. */
    private static final int DROP_INDEX = 4;
    /** The index related to pause function. */
    private static final int PAUSE_INDEX = 5;
    /** The index related to resume function. */
    private static final int RESUME_INDEX = 6;
    /** The error message. */
    private static final String ERROR_MESSAGE = "error";
    /** The panel to set key bindings. */
    private final SetControlsPanel myControls;
    /** The move listener. */
    private final TetrisKeyListener myKeyListener;
    /** The pause listener. */
    private final TetrisKeyListener myPauseKeyListener;
    /** The game board. */
    private final Board myBoard;
    /** The mapped key bindings. */
    private final List<String> myMappedKeys;
    /** The game GUI. */
    private final TetrisGUI myTetrisGUI;

    
    /**
     *  Default constructor that initializes the help menu.
     * 
     * @param theControls The key bindings.
     * @param theKeyListener The move key listener.
     * @param thePauseKeyListener The pause key listener.
     * @param theBoard The board.
     * @param theMappedKeys The key binding information.
     * @param theTetrisGUI The game's GUI.
     */
    public HelpMenu(final SetControlsPanel theControls,
                    final support.TetrisKeyListener theKeyListener,
                    final support.TetrisKeyListener thePauseKeyListener,
                    final Board theBoard, final List<String> theMappedKeys,
                    final TetrisGUI theTetrisGUI) {
        super("Help");
        setUpMenu();
        myControls = theControls;
        myKeyListener = theKeyListener;
        myPauseKeyListener = thePauseKeyListener;
        myBoard = theBoard;
        myMappedKeys = theMappedKeys;
        myTetrisGUI = theTetrisGUI;
    }

    /** Sets up the menu. */
    private void setUpMenu() {
        setMnemonic(KeyEvent.VK_H);
        
        final JMenuItem scoringItem = new JMenuItem("Points ");
        scoringItem.setMnemonic(KeyEvent.VK_P);
        scoringItem.addActionListener((theEvent) -> {
            myTetrisGUI.pauseGame();
            JOptionPane.showConfirmDialog(null,
                                        "Scoring is based on the NES version of Tetris."
                                        + "\n\nPlacing any block grants 4 points."
                                        + "\nA level is gained every 5 rows cleared."
                                        + "\nEach level is 10% faster than the previous."
                                        + "\nClearing multiple rows at a time "
                                        + "grant more points."
                                        + "\nPoints are multiplied by the curent level."
                                        + "\nOne line cleared: 40 Points."
                                        + "\nTwo lines cleared: 100 Points."
                                        + "\nThree lines cleared: 300 Points."
                                        + "\nFour lines cleared: 1200 Points.",
                                        "How Points Are Granted ", JOptionPane.PLAIN_MESSAGE);
            myTetrisGUI.resumeGame();
        });
        add(scoringItem);
        
        final JMenuItem keyItem = new JMenuItem("Set Controls");
        keyItem.setMnemonic(KeyEvent.VK_S);
        keyItem.addActionListener((theEvent) -> {
            myTetrisGUI.pauseGame();
            final int result = JOptionPane.showConfirmDialog(null, myControls, "Remap Keys",
                                        JOptionPane.CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION && !ERROR_MESSAGE.equals(
                                                                 myControls.getChars()[0])) {
                myKeyListener.clear();
                myPauseKeyListener.clearAll();
                final String[] chars = myControls.getChars().clone();
                myKeyListener.addKey(KeyEvent.getExtendedKeyCodeForChar(chars[0].charAt(0)),
                    () -> myBoard.rotateCW());
                myKeyListener.addKey(KeyEvent.getExtendedKeyCodeForChar(chars[1].charAt(0)),
                    () -> myBoard.right());
                myKeyListener.addKey(KeyEvent.getExtendedKeyCodeForChar(chars[2].charAt(0)),
                    () -> myBoard.left());
                myKeyListener.addKey(KeyEvent.getExtendedKeyCodeForChar(
                                                                 chars[STEP_INDEX].charAt(0)),
                    () -> myBoard.step());
                myKeyListener.addKey(KeyEvent.getExtendedKeyCodeForChar(
                                                                 chars[DROP_INDEX].charAt(0)),
                    () -> myBoard.drop());
                myPauseKeyListener.addKey(KeyEvent.getExtendedKeyCodeForChar(
                                                                 chars[PAUSE_INDEX].charAt(0)),
                    () -> myTetrisGUI.pauseGame());
                myPauseKeyListener.addKey(KeyEvent.getExtendedKeyCodeForChar(
                                                               chars[RESUME_INDEX].charAt(0)),
                    () -> myTetrisGUI.resumeGame());

                myMappedKeys.clear();
                myMappedKeys.add(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(
                                                                         chars[0].charAt(0))));
                myMappedKeys.add(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(
                                                                         chars[1].charAt(0))));
                myMappedKeys.add(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(
                                                                         chars[2].charAt(0))));
                myMappedKeys.add(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(
                                                              chars[STEP_INDEX].charAt(0))));
                myMappedKeys.add(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(
                                                              chars[DROP_INDEX].charAt(0))));
                myMappedKeys.add(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(
                                                              chars[PAUSE_INDEX].charAt(0))));
                myMappedKeys.add(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(
                                                              chars[RESUME_INDEX].charAt(0))));
            }
            
            if (result == JOptionPane.OK_OPTION && ERROR_MESSAGE.equals(
                                                               myControls.getChars()[0])) {
                JOptionPane.showConfirmDialog(null,
                                              "Missing or duplicate fields!",
                                              "Error setting key bindings!",
                                              JOptionPane.PLAIN_MESSAGE);
            }
            myTetrisGUI.resumeGame();
        });
        
        add(keyItem);
        
    }
}
