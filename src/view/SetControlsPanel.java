/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Display that allows the user to set new bindings.
 * 
 * @author eduardk
 * @version 5 Dec, 2017
 *
 */
public class SetControlsPanel extends JPanel {
    /** The serialVersionUID. */
    private static final long serialVersionUID = 2572364338004781018L;
    /** The size on input field. */
    private static final int INPUT_SIZE = 2;
    /** The third grid position. */
    private static final int GRID_THREE_POSITION = 3;
    /** The text field for rotation key binding. */
    private final JTextField myRotateField;
    /** The text field for right key binding. */
    private final JTextField myRightField;
    /** The text field for left key binding. */
    private final JTextField myLeftField;
    /** The text field for down key binding. */
    private final JTextField myDownField;
    /** The text field for drop key binding. */
    private final JTextField myDropField;
    /** The text field for pause key binding. */
    private final JTextField myPauseField;
    /** The text field for resume key binding. */
    private final JTextField myResumeField;
    
    /** Default constructor that initializes the binding controller. */
    public SetControlsPanel() {
        super();
        myRotateField = new JTextField("W", INPUT_SIZE);
        myRightField = new JTextField("D", INPUT_SIZE);
        myLeftField = new JTextField("A", INPUT_SIZE);
        myDownField = new JTextField("S", INPUT_SIZE);
        myDropField = new JTextField(" ", INPUT_SIZE);
        myPauseField = new JTextField("P", INPUT_SIZE);
        myResumeField = new JTextField("U", INPUT_SIZE);
        setUpBoard();
        addControlsListeners();
    }
    
    /** Adds controls listeners to every field. */
    private void addControlsListeners() {
        myResumeField.addKeyListener(new ControlsListener(myResumeField));
        myPauseField.addKeyListener(new ControlsListener(myPauseField));
        myRotateField.addKeyListener(new ControlsListener(myRotateField));
        myRightField.addKeyListener(new ControlsListener(myRightField));
        myLeftField.addKeyListener(new ControlsListener(myLeftField));
        myDownField.addKeyListener(new ControlsListener(myDownField));
        myDropField.addKeyListener(new ControlsListener(myDropField));
    }
    
    /**
     * Sets up the input fields into a North, East, South, West formation for the
     * different move keys. Pause and resume keys on the sides.
     */
    private void setUpBoard() {
        setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        final JPanel rotatePanel = new JPanel();
        rotatePanel.add(new JLabel("Rotate Key:"));
        rotatePanel.add(myRotateField);
        add(rotatePanel, c);
        
        c.gridx = 2;
        c.gridy = 1;
        final JPanel rightPanel = new JPanel();
        rightPanel.add(new JLabel("Right Key:"));
        rightPanel.add(myRightField);
        add(rightPanel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        final JPanel leftPanel = new JPanel();
        leftPanel.add(new JLabel("Left Key:"));
        leftPanel.add(myLeftField);
        add(leftPanel, c);
        
        c.gridx = 1;
        c.gridy = 2;
        final JPanel downPanel = new JPanel();
        downPanel.add(new JLabel("Down Key:"));
        downPanel.add(myDownField);
        add(downPanel, c);
        
        c.gridx = 1;
        c.gridy = GRID_THREE_POSITION;
        final JPanel dropPanel = new JPanel();
        dropPanel.add(new JLabel("Drop Key:"));
        dropPanel.add(myDropField);
        add(dropPanel, c);
        
        c.gridx = 0;
        c.gridy = GRID_THREE_POSITION;
        final JPanel pausePanel = new JPanel();
        pausePanel.add(new JLabel("Pause Key:"));
        pausePanel.add(myPauseField);
        add(pausePanel, c);
        
        c.gridx = 2;
        c.gridy = GRID_THREE_POSITION;
        final JPanel resumePanel = new JPanel();
        resumePanel.add(new JLabel("Resume Key:"));
        resumePanel.add(myResumeField);
        add(resumePanel, c);
    }

    /**
     * Puts all the text of the different text fields and puts them into an array.
     * Will return an error array if any of the fields are null or there are
     * duplicate inputs in the different fields.
     * 
     * @return An array of string(s).
     */
    public String[] getChars() {
        final String[] error = {"error"};
        String[] chars = {myRotateField.getText(), myRightField.getText(),
                       myLeftField.getText(), myDownField.getText(),
                       myDropField.getText(), myPauseField.getText(), myResumeField.getText()};
        boolean result = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] ==  null || "".equals(chars[i])) {
                result = true;
                break;
            }
            for (int j = i; j < i; j++) {
                if (KeyEvent.getExtendedKeyCodeForChar(chars[i].charAt(0))
                                == KeyEvent.getExtendedKeyCodeForChar(chars[j].charAt(0))) {
                    result = true;
                }
            }
        }
        
        if (result) {
            chars = error;
        }
        return chars.clone();
    }
    
    /**
     * Key Listener class that sets the text to an empty space. This setting happens
     * before the actual key is released so the text field will only contain a single
     * char.
     * 
     * @author eduardk
     * @version 5 Dec, 2017
     *
     */
    class ControlsListener extends KeyAdapter {
        /** The specific text field. */
        private final JTextField myField;
        /**
         * Default constructor that initializes the listener.
         * 
         * @param theField The text field.
         */
        ControlsListener(final JTextField theField) {
            super();
            myField = theField;
        }
        
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            myField.setText(null);
        }
    }
}
