/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package view;

import colorthemes.AbstractTheme;
import colorthemes.StandardTheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.TetrisPiece;
import support.ScoreKeeper;

/**
 * This is the GUI class for the tetris game. This class brings all the
 * different components together.
 * 
 * @author eduardk
 * @version 1 Dec, 2017
 *
 */
public class TetrisGUI extends JPanel implements PropertyChangeListener {
    /** The serialVersionUID. */
    private static final long serialVersionUID = 4213335979236391427L;
    /** The delay before the game progresses. */
    private static final int INITIAL_DELAY = 1000;
    /** The speed multiplier; 10 percent faster each level. */
    private static final double DELAY_MULTIPLIER = .9;
    /** The score keeper. */
    private final ScoreKeeper myScoreTracker;
    /** The key listener. */
    private final support.TetrisKeyListener myKeyListener; 
    /** The frame. */
    private final JFrame myWindow;
    /** The game board. */
    private final Board myBoard;
    /** The game timer. */
    private final Timer myTimer;
    /** The background. */
    private final Color myBackgroundColor;
    /** The game's logo. */
    private final ImageIcon myLogo;
    /** The pause key listener. */
    private support.TetrisKeyListener myPauseKeyListener;
    /** The next piece to fall. */
    private TetrisPiece myNextPiece;
    /** The score panel. */
    private JPanel mySPanel;
    /** The delay before progression. */
    private int myDelay;
    /** The key binding information. */
    private List<String> myMappedKeys;
    /** The control panel. */
    private JPanel myControlsPanel;
    /** The score pop-out frame. */
    private JFrame myScoreFrame;
    /** The right panel which holds game information. */
    private JPanel myRightPanel;
    /** The mouse listener. */
    private TetrisMouseListener myMouseListener;
    /** The controller that sets the key binding information. */
    private SetControlsPanel myControls;
    /** The item used to pause the game. */
    private JMenuItem myPauseGameItem;
    /** The item used to resume the game. */
    private JMenuItem myResumeGameItem;
    /** The item used to end the game. */
    private JMenuItem myEndGameItem;
    /** The next piece preview panel. */
    private PreviewBoard myPPanel;
    /** The tetris game preview panel. */
    private TetrisBoard myTPanel;
    
    /** Default constructor that initializes GUI. */
    public TetrisGUI() {
        super();
        myDelay = INITIAL_DELAY;
        myWindow = new JFrame("Tetris");
        myBoard = selectSize();
        myTimer = new Timer(INITIAL_DELAY, new TimeListener());
        myBackgroundColor = Color.DARK_GRAY.darker();
        myLogo = new ImageIcon("./images/logo.png");
        myKeyListener =  new support.TetrisKeyListener(myBoard, this);
        fillMappedKeys();
        myScoreTracker = new ScoreKeeper();
    }
    
    /** Fills the array with default key binding information. */
    private void fillMappedKeys() {
        myMappedKeys = new ArrayList<String>();
        myMappedKeys.add("W");
        myMappedKeys.add("D");
        myMappedKeys.add("A");
        myMappedKeys.add("S");
        myMappedKeys.add("Space");
        myMappedKeys.add("P");
        myMappedKeys.add("U");
        myControlsPanel = new ControlsPanel((ArrayList<String>) myMappedKeys);
        myBoard.addObserver((Observer) myControlsPanel);
    }
    
    /**
     * Creates a new tetris board with option of choosing a grid size.
     * 
     * @return The board with a specified size.
     */
    private Board selectSize() {
        final SetBoardSizePanel sizeSetter = new SetBoardSizePanel();
        final int result = JOptionPane.showConfirmDialog(null, sizeSetter,
                                                    "Select the size of the Tetris Board",
                                                    JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
        return new Board(sizeSetter.getBoardWidth(), sizeSetter.getBoardHeight());
    }

    /** Creates and shows the GUI. */
    public void start() {
        myWindow.setContentPane(this);
        setUpComponents();
        myWindow.setResizable(false);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.pack();
        myWindow.setMinimumSize(myWindow.getSize());
        // myWindow.setResizable(false);
        myTimer.start();
    }
    
    /** Sets up the panels and adds observers. */
    public void setUpComponents() {
        final AbstractTheme theme = new StandardTheme();
        myPauseKeyListener =  new support.TetrisKeyListener(myBoard, this);
        myPauseKeyListener.clearAll();
        myPauseKeyListener.addKey(KeyEvent.VK_P, () -> this.pauseGame());
        myPauseKeyListener.addKey(KeyEvent.VK_U, () -> this.resumeGame());
        myWindow.addKeyListener(myPauseKeyListener);
        
        myScoreTracker.addPropertyChangeListener(this);
        myControls = new SetControlsPanel();
        myWindow.setIconImage(myLogo.getImage());
        myWindow.addKeyListener(myKeyListener);
        myBoard.addObserver((Observer) myScoreTracker);
        myBoard.addObserver(new TetrisObserver());
        myTPanel = new TetrisBoard(myBoard, theme);
        myBoard.addObserver((Observer) myTPanel);
        myBoard.newGame();
        myPPanel = new PreviewBoard(myNextPiece, theme);
        myBoard.addObserver((Observer) myPPanel);
        mySPanel = new ScorePanel(myScoreTracker);
        myBoard.addObserver((Observer) mySPanel);
        
        setLayout(new BorderLayout());
        setBackground(myBackgroundColor);
        myWindow.setJMenuBar(createMenuBar());

        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(myBackgroundColor);
        leftPanel.add(myTPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        
        myRightPanel = new JPanel();
        myRightPanel.setLayout(new BoxLayout(myRightPanel, BoxLayout.Y_AXIS));
        myRightPanel.setBackground(myBackgroundColor);
        myRightPanel.add(myPPanel);
        myRightPanel.add(myControlsPanel);
        
        mySPanel.addPropertyChangeListener(this);
        myRightPanel.add(mySPanel);
        myMouseListener = new TetrisMouseListener();
        mySPanel.addMouseListener(myMouseListener);
        
        add(myRightPanel, BorderLayout.EAST);
    }

    /**
     * Creates a menu bar with menus.
     * 
     * @return The menu bar with all menus.
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(createOptionsMenu());
        menuBar.add(new HelpMenu(myControls, myKeyListener,
            myPauseKeyListener, myBoard, myMappedKeys, this));
        menuBar.add(new ThemeMenu(myPPanel, myTPanel));
        return menuBar;
    }
    
    /**
     * Creates the options menu which allows the user to start a new game, pause,
     * and resume.
     * 
     * @return The options menu.
     */
    private JMenu createOptionsMenu() {
        final JMenu optionMenu = new JMenu("Options");
        optionMenu.setMnemonic(KeyEvent.VK_O);
        
        final JMenuItem newGameItem = new JMenuItem("New game");
        newGameItem.setMnemonic(KeyEvent.VK_N);
        newGameItem.addActionListener((theEvent) -> {
            myEndGameItem.setEnabled(true);
            myScoreTracker.resetScore();
            myWindow.removeKeyListener(myKeyListener);
            myWindow.addKeyListener(myPauseKeyListener);
            myDelay = INITIAL_DELAY;
            myTimer.setDelay(myDelay);
            resumeGame();
            myBoard.newGame();
        });
        
        myPauseGameItem = new JMenuItem("Pause game");
        myPauseGameItem.setMnemonic(KeyEvent.VK_P);
        myPauseGameItem.addActionListener((theEvent) -> {
            pauseGame();
        });
        
        myResumeGameItem = new JMenuItem("Resume game");
        myResumeGameItem.setEnabled(false);
        myResumeGameItem.setMnemonic(KeyEvent.VK_R);
        myResumeGameItem.addActionListener((theEvent) -> {
            resumeGame();
        });
        
        myEndGameItem = new JMenuItem("End game");
        myEndGameItem.setMnemonic(KeyEvent.VK_E);
        myEndGameItem.addActionListener((theEvent) -> {
            endGame();
            JOptionPane.showConfirmDialog(null, "Come on, you can do better!",
                 "You ended the game!", JOptionPane.PLAIN_MESSAGE);
        });
        
        optionMenu.add(newGameItem);
        optionMenu.add(myPauseGameItem);
        optionMenu.add(myResumeGameItem);
        optionMenu.add(myEndGameItem);
        return optionMenu;
    }
    
    /** Stops the timer, removes the key listener, and disables this button. */
    public void pauseGame() {
        myTimer.stop();
        myWindow.removeKeyListener(myKeyListener);
        myPauseGameItem.setEnabled(false);
        myResumeGameItem.setEnabled(true);
    }
    
    /** Starts the timer, adds the key listener and disables this button. */
    public void resumeGame() {
        myWindow.removeKeyListener(myKeyListener);
        myTimer.start();
        myWindow.addKeyListener(myKeyListener);
        myResumeGameItem.setEnabled(false);
        myPauseGameItem.setEnabled(true);
    }
    
    /** Stops the timer, removes the key listener and disables all buttons. */
    private void endGame() {
        myTimer.stop();
        myWindow.removeKeyListener(myKeyListener);
        myWindow.removeKeyListener(myPauseKeyListener);
        myResumeGameItem.setEnabled(false);
        myPauseGameItem.setEnabled(false);
        myEndGameItem.setEnabled(false);
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("level up".equals(theEvent.getPropertyName())) {
            // Every level up the delay is 10% shorter
            myDelay *= DELAY_MULTIPLIER;
            myTimer.setDelay(myDelay);
        }
    }

    /**
     * The observer class for the game. Looks at the Board for any changes.
     * 
     * @author eduardk
     * @version 1 Dec, 2017
     *
     */
    class TetrisObserver implements Observer {
        @Override
        public void update(final Observable theO, final Object theArg) {
            if (theArg instanceof Boolean) {
                myTimer.stop();
                myWindow.removeKeyListener(myKeyListener);
                endGame();
                JOptionPane.showConfirmDialog(null, "Better luck next time..",
                                     "Game Over!", JOptionPane.PLAIN_MESSAGE);
            } else if (theArg instanceof TetrisPiece) {
                myNextPiece = (TetrisPiece) theArg;
            }
        }
    }
    
    /**
     * The timer class for the game. Used for progressing the game.
     * 
     * @author eduardk
     * @version 1 Dec, 2017
     *
     */
    private class TimeListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.step();
        }
    }
    
    /**
     * The mouse listener. Used for creating a pop-out frame when the score panel is
     * clicked. Closing the frame puts the score panel back into the main frame.
     * 
     * @author eduardk
     * @version 4 Dec, 2017
     *
     */
    private class TetrisMouseListener extends MouseAdapter {
        /** The score frame's width. */
        private static final int FRAME_WIDTH = 240;
        /** The score frame's height. */
        private static final int FRAME_HEIGHT = 160;
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (theEvent.getSource().equals(mySPanel)) {
                myScoreFrame = new JFrame("Score");
                myScoreFrame.setContentPane(mySPanel);
                myScoreFrame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
                myScoreFrame.setVisible(true);
                myScoreFrame.setResizable(false);
                myScoreFrame.setLocationRelativeTo(null);
                myScoreFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(final java.awt.event.WindowEvent theEvent) {
                        myRightPanel.add(mySPanel);
                        mySPanel.addMouseListener(myMouseListener);
                        repaint();
                        revalidate();
                    }
                });
                mySPanel.removeMouseListener(myMouseListener);
                myWindow.requestFocus();
                repaint();
                revalidate();
            } 
        }
    }
}
