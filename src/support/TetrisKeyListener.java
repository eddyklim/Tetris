/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package support;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import model.Board;
import view.TetrisGUI;

/**
 * Holds all the key bindings for the game.
 * 
 * @author eduardk
 * @version 1 Dec, 2017
 *
 */
public class TetrisKeyListener extends KeyAdapter {
    /** The game board. */
    private final Board myBoard;
    /** The key bindings. */
    private final Map<Integer, Runnable> myKeyMap;
    /** The GUI. */
    private final TetrisGUI myTetrisGUI;
    
    
    /**
     * Default constructor that initializes the KeyListener.
     * 
     * @param theBoard The game Board.
     * @param theTetrisGUI The game's GUI.
     */
    public TetrisKeyListener(final Board theBoard, final TetrisGUI theTetrisGUI) {
        super();
        myTetrisGUI = theTetrisGUI;
        myBoard = theBoard;
        myKeyMap = new HashMap<Integer, Runnable>();
        mapDefaultKeys();
        
    }
    
    /** Sets up the default game bindings. */
    private void mapDefaultKeys() {
        myKeyMap.put(KeyEvent.VK_D, () -> myBoard.right());
        myKeyMap.put(KeyEvent.VK_RIGHT, () -> myBoard.right());
        myKeyMap.put(KeyEvent.VK_A, () -> myBoard.left());
        myKeyMap.put(KeyEvent.VK_LEFT, () -> myBoard.left());
        myKeyMap.put(KeyEvent.VK_S, () -> myBoard.step());
        myKeyMap.put(KeyEvent.VK_DOWN, () -> myBoard.step());
        myKeyMap.put(KeyEvent.VK_W, () -> myBoard.rotateCW());
        myKeyMap.put(KeyEvent.VK_UP, () -> myBoard.rotateCW());
        myKeyMap.put(KeyEvent.VK_SPACE, () -> myBoard.drop());
        myKeyMap.put(KeyEvent.VK_P, () -> myTetrisGUI.pauseGame());
        myKeyMap.put(KeyEvent.VK_U, () -> myTetrisGUI.resumeGame());
    }
    
    /**
     * Used to re-map any key binding, removes old binding before adding a new one.
     * 
     * @param theKey The key.
     * @param theRun The binding.
     */
    public void addKey(final Integer theKey, final Runnable theRun) {
        final int keyCode = theKey;
        if (myKeyMap.containsKey(keyCode)) {
            myKeyMap.remove(keyCode);
        }
        myKeyMap.put(keyCode, theRun);
    }

    @Override
    public void keyPressed(final KeyEvent theEvent) {
        final int keyCode = theEvent.getKeyCode();
        if (myKeyMap.containsKey(keyCode)) {
            myKeyMap.get(keyCode).run();
        }
    }

    /** Clears the map of letter bindings. */
    public void clear() {
        myKeyMap.clear();
        myKeyMap.put(KeyEvent.VK_RIGHT, () -> myBoard.right());
        myKeyMap.put(KeyEvent.VK_LEFT, () -> myBoard.left());
        myKeyMap.put(KeyEvent.VK_DOWN, () -> myBoard.step());
        myKeyMap.put(KeyEvent.VK_UP, () -> myBoard.rotateCW());
    }
    

    /** Clears the map of all bindings except pause. */
    public void clearAll() {
        myKeyMap.clear();
    }
}